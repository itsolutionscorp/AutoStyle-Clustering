class Phrase

    def initialize phrase
        @phrase = phrase
    end

    def word_count
        word_count = Hash.new(0)
        split_phrase.each_with_object(word_count) { |word| word_count.store(word, word_count[word]+1) }
    end

    private

    def split_phrase
        @phrase.downcase.scan(/\w+'?\w?/)
    end

end
