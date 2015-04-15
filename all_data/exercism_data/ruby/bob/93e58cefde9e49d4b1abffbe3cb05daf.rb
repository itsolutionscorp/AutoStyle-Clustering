class Phrase
	attr_reader :string

	def initialize(string)
		@string = string
	end
	def word_count
		
		words = string.downcase.gsub(/(\W)/, " ").split
		# make the hash default to 0.
		hash = Hash.new(0)

		words.each do |word|
			hash[word] += 1
		end

		return hash
	end
end
