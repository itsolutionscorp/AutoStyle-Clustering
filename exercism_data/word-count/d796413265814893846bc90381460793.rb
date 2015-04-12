class Phrase

    def initialize phrase
        @h = {}
        @phrase = phrase.downcase.gsub(/[\W_]/, " ").split(" ")
        count_words
    end

    def word_count
        @h
    end

    private

    def count_words
        @phrase.each do |word|
            if @h[word]
                @h[word] += 1
            else
                @h[word] = 1
            end
        end
    end
end
