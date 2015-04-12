class Phrase 
    def initialize words
        @words = words
    end

    def word_count
        map_words @words
    end

    private
    def map_words words
        word_list = words.downcase.scan /\w+/
        word_list.reduce(Hash.new(0)) {|acc,word|
            acc[word] = acc[word] + 1
            acc
        }
    end    
end
