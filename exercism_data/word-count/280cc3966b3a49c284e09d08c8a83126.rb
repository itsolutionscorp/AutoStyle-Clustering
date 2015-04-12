class Phrase
    attr_reader:word_count
    def initialize(w)
        @word_count = {}
        w.downcase.scan(/[\w']+/).each { |w| @word_count[w] = (@word_count[w] || 0)+1 }
    end
end
