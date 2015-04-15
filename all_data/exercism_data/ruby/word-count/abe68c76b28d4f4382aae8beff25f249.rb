class Phrase
    attr_reader :word_count

    def initialize(phrase)
        @phrase = phrase
        @word_count = make_word_count
    end

    private

    def downcase_words
        @phrase.downcase.split(/[^a-zA-Z0-9']+/)
    end

    def make_word_count
        downcase_words.reduce(Hash.new { |key| 0 }) do |counts, word|
            counts[word] += 1
            counts
        end
    end

end
