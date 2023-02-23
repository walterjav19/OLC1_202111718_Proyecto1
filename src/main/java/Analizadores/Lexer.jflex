/*package e importaciones*/

package Analizadores;
import java_cup.runtime.Symbol;
import java.util.ArrayList;
import java.util.List;

%%

%{
    //Codigo de usuario en sintaxis java
    //Agregar clases variables arreglos tc
    public List<Errores.ErrorLexico> ErroresLexicos = new ArrayList<Errores.ErrorLexico>();
%}


//directivas
%class Lexico
%public
%cup
%char
%column
%full
%line
%unicode

%init{
    yyline=1;
    yychar=0;
%init}


//Expresiones Regulares
BLANCOS=[ \r\t]+
D=[0-9]+
DD=[0-9]+("."[  |0-9]+)?
letras=[a-zA-Z_]+
digito=[0-9]
letra=[a-zA-Z]
any=[^!]

LineTerminator= \r|\n|\r\n
InputCharacter=[^\r\n]

comentariosimple="//" {InputCharacter}* {LineTerminator}?
comentariomultilinea="<!" {any}* "!>"
%%

/*Reglas Semanticas*/
{letra} {return new Symbol(sym.LETR,yyline,yychar, yytext());}
"CONJ" {return new Symbol(sym.CONJUNTO,yyline,yychar,yytext());} 
{letras}({letras}|{D})* {return new Symbol(sym.IDENT,yyline,yychar, yytext());}
{digito} {return new Symbol(sym.DIGITO,yyline,yychar, yytext());}



";" {return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}
":" {return new Symbol(sym.DOSPTO,yyline,yychar, yytext());}
"~" {return new Symbol(sym.VIRG,yyline,yychar, yytext());}
"(" {return new Symbol(sym.PARIZQ,yyline,yychar, yytext());} 
")" {return new Symbol(sym.PARDER,yyline,yychar, yytext());} 
"[" {return new Symbol(sym.CORIZQ,yyline,yychar, yytext());} 
"]" {return new Symbol(sym.CORDER,yyline,yychar, yytext());}
"{" {return new Symbol(sym.BRAIZQ,yyline,yychar, yytext());}
"}" {return new Symbol(sym.BRADER,yyline,yychar, yytext());}
"->" {return new Symbol(sym.ASIGN,yyline,yychar, yytext());}
"%"  {return new Symbol(sym.PERCEN,yyline,yychar, yytext());}
"."  {return new Symbol(sym.CONCAT,yyline,yychar, yytext());}
"|"  {return new Symbol(sym.DIS,yyline,yychar, yytext());}
"*" {return new Symbol(sym.KLE,yyline,yychar, yytext());} 
"+" {return new Symbol(sym.MAS,yyline,yychar, yytext());} 
"?" {return new Symbol(sym.INTER,yyline,yychar, yytext());}

\" {return new Symbol(sym.COM,yyline,yychar, yytext());} 

\n {yychar=0;}
\t {yychar=yychar+4;}
{comentariosimple} {/*Ignorar Espacios Comentarios*/}
{BLANCOS} {/*Ignorar Espacios en Blanco*/}
{comentariomultilinea} {/*Ignorar Espacios*/} 

{D} {return new Symbol(sym.ENTERO,yyline,yychar, yytext());} 
{DD} {return new Symbol(sym.DECIMAL,yyline,yychar, yytext());} 

. {
    Errores.ErrorLexico err=new Errores.ErrorLexico(yytext(),yyline,yychar);
    ErroresLexicos.add(err);
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}

