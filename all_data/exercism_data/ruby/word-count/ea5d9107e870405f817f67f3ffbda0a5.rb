class Phrase
    def initialize(phrase = '')
        @phrase = phrase
    end

    def word_count
        counts = Hash.new
        counts.default = 0
        @phrase.downcase.split(/\W+/).each { |word| counts[word] += 1 }
        counts
    end
end
