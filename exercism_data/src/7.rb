class Phrase

    def initialize phrase
        @words = phrase.to_s.downcase.scan(/\w+/)
    end

    def word_count
        @words.each_with_object(Hash.new(0)) do |word, counter|  
            counter[word] += 1
        end
    end
end
