class Phrase
	def initialize(str)
		@arr = str.downcase.scan(/\w+/)
	end
	def word_count
		hash = {}
		@arr.each do |x|
			if hash.has_key?(x)
				hash[x] += 1
			else 
				hash[x] = 1
			end
		end
		return hash
	end
end
