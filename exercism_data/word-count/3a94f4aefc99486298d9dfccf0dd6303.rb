class Phrase

    def initialize phrase
        @phrase = phrase.to_s.downcase
    end

    def word_count
        @phrase.scan(/\w+/).each_with_object(Hash.new(0)) do |word, counter|  
            counter[word] = counter[word] + 1
        end
    end
end
