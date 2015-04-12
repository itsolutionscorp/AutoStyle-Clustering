class Phrase

    attr_reader :word_count
    
    def initialize( phrase )
        words = phrase.scan( /[-'A-Za-z0-9]+/ ).map( &:downcase )
        
        @word_count = Hash.new( 0 )
        words.each { |word| @word_count[word] += 1 }
    end
end
