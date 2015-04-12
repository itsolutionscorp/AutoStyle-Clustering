class Phrase
	attr_reader :word_count
    def initialize words
    	@word_count = calculate(words)
    end

    def calculate words
    	words.gsub(/[:!&@$%^.]/, '').downcase.split(/[ ,]/).reject(&:empty?).inject({}) { |wordmap, w|
            single = {w => 1}
            wordmap[w].nil? ? wordmap.merge!(single) : wordmap[w] += 1
            wordmap
		}
    end
end
