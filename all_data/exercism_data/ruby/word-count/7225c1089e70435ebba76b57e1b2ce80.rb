class Phrase
	def initialize(str)
		@res_hash = Hash.new(0)
		str.downcase.scan(/[\w']+/).reduce(@res_hash) do |acc, v|
			acc[v] += 1
			acc
		end
	end
	def word_count
		@res_hash
	end
end
