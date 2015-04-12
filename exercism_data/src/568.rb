class Phrase
    def initialize(phrase)
        @words = phrase.delete(",!&@$%^&:")
                       .downcase
                       .split
    end

    def word_count
        @words.each_with_object({}) do |word, words|
            words[word] ||= 0
            words[word] += 1
        end
    end
end
