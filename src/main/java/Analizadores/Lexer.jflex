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
    public List<Symbol> T_SIMBOLOS = new ArrayList<Symbol>();
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
as=[!-/] | [:-@] | [\[-`] | [\{-\}]
cadena="\"" [^\"]* "\""
LineTerminator= \r|\n|\r\n
InputCharacter=[^\r\n]
spe="\\""n" | "\\""\'" | "\\""\""

comentariosimple="//" {InputCharacter}* {LineTerminator}?
comentariomultilinea="<!" {any}* "!>"
%%

/*Reglas Semanticas*/
{letra} {Symbol s = new Symbol(sym.LETR,yyline,yychar, yytext());
        T_SIMBOLOS.add(s);
        return new Symbol(sym.LETR,yyline,yychar, yytext());}


"CONJ" {Symbol s = new Symbol(sym.CONJUNTO,yyline,yychar, yytext());
        T_SIMBOLOS.add(s);
        return new Symbol(sym.CONJUNTO,yyline,yychar,yytext());} 


{letras}({letras}|{D})* {Symbol s = new Symbol(sym.IDENT,yyline,yychar, yytext());
                        T_SIMBOLOS.add(s);
                        return new Symbol(sym.IDENT,yyline,yychar, yytext());}


{digito} {Symbol s = new Symbol(sym.DIGITO,yyline,yychar, yytext());
         T_SIMBOLOS.add(s);
         return new Symbol(sym.DIGITO,yyline,yychar, yytext());}

";" {Symbol s = new Symbol(sym.PTCOMA,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.PTCOMA,yyline,yychar, yytext());}


":" {Symbol s = new Symbol(sym.DOSPTO,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.DOSPTO,yyline,yychar, yytext());}


"~" {Symbol s = new Symbol(sym.VIRG,yyline,yychar, yytext());
     T_SIMBOLOS.add(s);
     return new Symbol(sym.VIRG,yyline,yychar, yytext());}


"(" {Symbol s = new Symbol(sym.PARIZQ,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.PARIZQ,yyline,yychar, yytext());} 


")" {Symbol s = new Symbol(sym.PARDER,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.PARDER,yyline,yychar, yytext());}

 
"[" {Symbol s = new Symbol(sym.CORIZQ,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.CORIZQ,yyline,yychar, yytext());}

 
"]" {Symbol s = new Symbol(sym.CORDER,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.CORDER,yyline,yychar, yytext());}


"{" {Symbol s = new Symbol(sym.BRAIZQ,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.BRAIZQ,yyline,yychar, yytext());}


"}" {Symbol s = new Symbol(sym.BRADER,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.BRADER,yyline,yychar, yytext());}


"->" {Symbol s = new Symbol(sym.ASIGN,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.ASIGN,yyline,yychar, yytext());}


"%%"  {Symbol s = new Symbol(sym.PERCEN,yyline,yychar, yytext());
      T_SIMBOLOS.add(s);
      return new Symbol(sym.PERCEN,yyline,yychar, yytext());}


"."  {Symbol s = new Symbol(sym.CONCAT,yyline,yychar, yytext());
     T_SIMBOLOS.add(s);
     return new Symbol(sym.CONCAT,yyline,yychar, yytext());}


"|"  {Symbol s = new Symbol(sym.DIS,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.DIS,yyline,yychar, yytext());}


"*" {Symbol s = new Symbol(sym.KLE,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.KLE,yyline,yychar, yytext());} 


"+" {Symbol s = new Symbol(sym.MAS,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.MAS,yyline,yychar, yytext());} 


"?" {Symbol s = new Symbol(sym.INTER,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.INTER,yyline,yychar, yytext());}


"," {Symbol s = new Symbol(sym.COMA,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.COMA,yyline,yychar, yytext());}


\" {Symbol s = new Symbol(sym.COM,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.COM,yyline,yychar, yytext());}


{spe} {Symbol s = new Symbol(sym.SPECHAR,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.SPECHAR,yyline,yychar, yytext());}
 

{as} {Symbol s = new Symbol(sym.ASCII,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.ASCII,yyline,yychar, yytext());}


{cadena} {Symbol s = new Symbol(sym.CADENA,yyline,yychar, yytext());
    T_SIMBOLOS.add(s);
    return new Symbol(sym.CADENA,yyline,yychar, yytext());}


\n {yychar=0;}
\t {yychar=yychar+4;}
{comentariosimple} {/*Ignorar Espacios Comentarios*/}
{BLANCOS} {/*Ignorar Espacios en Blanco*/}
{comentariomultilinea} {/*Ignorar Espacios*/} 

 

. {
    Errores.ErrorLexico err=new Errores.ErrorLexico(yytext(),yyline,yychar);
    ErroresLexicos.add(err);
    System.out.println("Este es un error lexico: "+yytext()+", en la linea: "+yyline+", en la columna: "+yychar);
}

