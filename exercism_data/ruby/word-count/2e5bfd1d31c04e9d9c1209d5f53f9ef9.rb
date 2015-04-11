class Phrase

    def initialize phrase
        @phrase = phrase.to_s.downcase
    end

    def words
       words = @phrase.scan(/\w+/)
    end

    def word_counter
       word_counter = Hash.new(0)
    end

    def word_count
        words.each_with_object(word_counter) do |word, counter|  
            counter[word] = counter[word] + 1
        end
    end
end
