class Phrase < String 

	def initialize(string)
		super
	end

	def word_count
		words = self.downcase.scan(/[\w\']+/)
		words.each_with_object(Hash.new(0)) do |word, hash| 
			hash[word] += 1
		end
	end
end
