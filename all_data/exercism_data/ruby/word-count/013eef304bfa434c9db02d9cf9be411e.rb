class Phrase
	def initialize(s)
		@s = s
	end

	def word_count
		count = {}
		@s.scan(/[\w']+/).each do |w|
			w = w.downcase
			if count[w]
				count[w] += 1
			else
				count[w] = 1
			end
		end
		return count
	end
end
