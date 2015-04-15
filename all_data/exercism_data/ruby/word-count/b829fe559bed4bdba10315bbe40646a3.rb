class Phrase
    attr_reader :phrase

    def initialize(phrase)
        @phrase = phrase
    end

    def words
        @phrase.downcase.scan(/\w+/)
    end

    def word_count
        words.each_with_object(Hash.new(0)) { |word, counts| counts[word] += 1 }
    end
end
