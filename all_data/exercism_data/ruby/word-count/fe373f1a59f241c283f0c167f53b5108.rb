class Phrase
    def initialize(str)
        @words = str
    end

    def word_count
        get_words(@words)
            .each_with_object(Hash.new(0)) { |w, counts|
                counts[w] += 1
            }
    end

    private
    def get_words(str)
        str.downcase
            .scan(/\w+/)
    end
end
