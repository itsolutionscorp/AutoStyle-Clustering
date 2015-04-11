class Phrase

    def word_count
        @words.each_with_object({}) do |word, words|
            words[word] ||= 0
            words[word] += 1
        end
    end
    
    private 
    
    def initialize(phrase)
        @words = phrase.delete(",!&@$%^&:")
                       .downcase
                       .split
    end
end
