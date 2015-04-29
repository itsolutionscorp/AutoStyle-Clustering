class Words
    def initialize source
        @words = source.split %r{\W}
    end

    def count
        word_count = {}
        @words.each do |word|
            word_count = add_word(word_count, word)
        end
        word_count
    end

    def add_word(count, word)
        return count if word.empty?
        word = word.downcase
        count.update(word => 1) { |key, total, inc| total+inc }
    end
end
