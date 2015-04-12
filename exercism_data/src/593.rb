class Phrase
    def initialize text
        @text = text
    end

    def word_count
        @text.scan(/[\w']+/).reduce(Hash.new 0) do |count, word|
            count[word.downcase] += 1; count
        end
    end
end
