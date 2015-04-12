class Phrase
	def initialize(str)
		@res_hash =  str.downcase.scan(/[\w']+/).reduce({}) do |acc, v|
			acc[v] = acc.fetch(v, 0) + 1
			acc
		end
	end
	def word_count
		@res_hash
	end
end
