class FoodChainSong
    
    ANIMALS         = %w[fly spider bird cat dog goat cow horse]
    descriptions    = [ "Perhaps she'll die.", 
                        "It wriggled and jiggled and tickled inside her.",
                        "How absurd to swallow a bird!",
                        "Imagine that, to swallow a cat!",
                        "What a hog, to swallow a dog!",
                        "Just opened her throat and swallowed a goat!",
                        "I don't know how she swallowed a cow!",
                        "She's dead, of course!"
                      ]
    VERSE_LINES     = ANIMALS.zip descriptions
        
    def verse( n )
        entry = VERSE_LINES[ n - 1 ]
        
        lines = [ "I know an old lady who swallowed a #{ entry[0] }." ]
        
        if n > 1
            lines << entry[ 1 ]
            return lines.push( "" ).join( "\n" ) if n == ANIMALS.count
               
            (2..n).each do |i|
                lines << "She swallowed the #{ entry[ 0 ] } to catch the #{ FoodChainSong.catchee( n - i, n ) }."
                entry = VERSE_LINES[ n - i ]
            end
        end
        
        lines << "I don't know why she swallowed the fly. #{ entry[ 1 ] }"
        lines << ""
        
        lines.join( "\n" )
    end
    
    def verses( from, to )
        (from..to).map { |n| verse n }.push( "" ).join( "\n" )
    end
    
    def sing
        verses(1, 8)
    end
    
    def self.catchee( i, n )
        VERSE_LINES[ i ][ 0 ] + (
            ( n > 2 && ANIMALS[ i ] == 'spider' ) ? 
                " that wriggled and jiggled and tickled inside her" :
                ""
            )
    end
    
end
