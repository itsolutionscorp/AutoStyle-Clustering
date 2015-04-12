class Phrase
	attr_reader :word_count
    def initialize words
    	@word_count = calculate(words)
    end

    def calculate words
    	words.gsub(/[:!&@$%^.]/, '').downcase.split(/[ ,]/).reject(&:empty?).inject({}) { |wordmap, w|
            if wordmap[w].nil?
                a = {w => 1}
                wordmap.merge! a
            else
                wordmap[w] += 1
            end
            wordmap
		}
    end
end
