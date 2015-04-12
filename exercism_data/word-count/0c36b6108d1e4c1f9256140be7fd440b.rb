class Phrase
    def initialize(phrase)
        @phrase = phrase
    end

    def word_count
        @counts = Hash.new { |key| 0 }
        downcase_words.each do |word|
            @counts[word] += 1
        end
        @counts
    end

    private

    def downcase_words
        @phrase.downcase.split /[^a-zA-Z0-9']+/
    end

end
