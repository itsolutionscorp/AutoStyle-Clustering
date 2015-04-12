class Phrase
	attr_reader :word_count
    def initialize words
    	@word_count = calculate(words)
    end

    def calculate words
    	words.gsub(/[^a-zA-Z0-9_']/, ' ').downcase.split.each_with_object(Hash.new(0)) { |w, wordmap|
    		wordmap[w] += 1
    	}
    end
end
