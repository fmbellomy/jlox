package jlox.interpreter;

class Token{
    final TokenType type;
    final String lexeme;
    final Object literal;
    final int line;
    Token(TokenType type, String lexeme, Object literal, int line){
        this.type = type;
        this.lexeme = lexeme;
        this.literal = literal;
        this.line = line;
    }
    public String toString(){
        return String.format("{\n    Type: %s\n    Lexeme: %s\n    Literal: %s\n}",type,lexeme,literal);
    }
}
