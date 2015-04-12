class Phrase

    def initialize phrase
        words = phrase.downcase.gsub(/[\W_]/, " ").split(" ")
        @values = words.inject({}) do |hash, word|
            if hash[word] 
                hash[word] += 1
            else
                hash[word] = 1
            end
            hash
        end
    end

    def word_count
        @values
    end

end
