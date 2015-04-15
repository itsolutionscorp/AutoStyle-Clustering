class Binary
    def initialize( binary )
        @bits = binary =~ /\A[01]*\z/ ? binary.chars : []
    end
    
    def to_base( x )
        @bits.reverse.inject( [ 0, 0 ] ) do | ( i, v ), c |
            [ i + 1, v + ( c == "0" ? 0 : 2 ** i ) ]
        end.last.to_s( x )
        
        # For decimal it doesn't need the to_s and it can be easily 
        # implemented anyway by having a 36 character string and 
        # reducing the integer back to a string, but I didn't feel
        # like it.
    end
    
    def to_octal
        to_base( 8 )
    end
    
    def to_decimal
        to_base( 10 ).to_i
    end
    
    def to_hexadecimal
        to_base( 16 )
    end
    
end
