class Phrase
    def initialize(str)
        @words = str
    end

    def word_count
        count_words(@words)
    end

    private
    def count_words(str)
        str.downcase
            .scan(/\w+/)
            .select { |w| !w.empty? }
            .each_with_object(Hash.new(0)) { |w, counts| 
                counts[w] = counts[w] + 1 
            }
    end
end
