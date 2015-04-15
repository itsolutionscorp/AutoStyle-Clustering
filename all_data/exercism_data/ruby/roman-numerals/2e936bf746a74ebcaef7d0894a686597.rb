module RomanHelper
       
    # I thought this looked pretty with the alignment hehe    
    DISPLAY_LETTERS = %w[ M     D    C    L   X   V  I ]
    LETTER_VALUES   =   [ 1000, 500, 100, 50, 10, 5, 1 ]
    MAPPING = DISPLAY_LETTERS.zip LETTER_VALUES
    
    ##
    # Gets the previous letter ( X will give L, M gives M )
    #
    def self.prev( letter, count )
        i = [ DISPLAY_LETTERS.index( letter ) - 1, 0 ].max
        
        # Skip every other (D/L/V) when we already found one
        #
        # i%2 == 1: This ensures that VIIII -> IX, LXL -> XC and DCD -> CM
        # count == 0: This ensures that IIII -> IV, XXXX -> XL and CCCC -> CD
        #
        DISPLAY_LETTERS[ i > 0 && ( count > 0 && i % 2 == 1 ) ? i - 1 : i ]
    end
    
    ##
    # Subtracts the letter from the current ( so I, X will give IX )
    #
    def self.subtract( letter, current )
        super_unit = prev( letter, current.length ).clone
        
        # If currently filled with a letter that is not the super unit 
        # (i.e. D, L or V), replace the letter with the super unit. We are
        # clearly trying to display 900, 400, 90, 40 or 9
        current = super_unit if current.length == 0 || super_unit != current[ 0 ]
        
        current.insert( -2, letter )
    end
    
end

class Fixnum

    def to_roman
        n = self
        
        RomanHelper::MAPPING.inject( [] ) do |memo, (letter, value) |
        
            # How many times does this letter fit in the remainder?
            quotient, n = n.divmod value
            
            # Max = 3 times, unless 'M'
            if quotient == 4 && !memo.empty?
                memo[ memo.size - 1 ] = RomanHelper.subtract( letter, memo[ memo.size - 1 ] )
              
            # Add up to 3 times this letter
            else
                memo << letter * quotient 
            end
               
            memo
        end.join
    end
end
