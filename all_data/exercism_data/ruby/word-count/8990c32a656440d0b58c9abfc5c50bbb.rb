class Phrase
    attr_reader:word_count
    def initialize(w)
        @word_count = Hash.new(0)
        w.downcase.scan(/[\w']+/).each { |w| @word_count[w] += 1 }
    end
end
