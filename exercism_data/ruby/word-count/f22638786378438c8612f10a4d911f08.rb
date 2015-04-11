class Phrase 
    def initialize words
        @words_map = map_words words
    end

    def word_count
        return @words_map
    end

    private
    def sum_word(acc, word)
        acc[word] = (acc[word] || 0) + 1
    end

    def map_words words
        word_list = words.downcase.scan /\w+/
        word_list.reduce(Hash.new) {|acc,word| 
            acc[word] = (acc[word] || 0) + 1
            acc
        }
    end    
end
