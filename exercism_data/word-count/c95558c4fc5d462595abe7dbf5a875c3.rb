class Phrase
    def initialize(str)
        @counts = Hash.new
        count_words(str)
    end

    def word_count
        @counts
    end

    private
    def count_words(str)
        str.downcase.split(/[^a-z0-9]/).each { |w|
            if w != ''
                @counts[w] = (@counts[w] || 0) + 1
            end
        }
    end
end
