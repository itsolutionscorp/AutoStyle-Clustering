class Phrase < Struct.new(:input)
	
	def word_count
		words.reduce(Hash.new(0)) do |hsh, word|
			hsh[word] += 1
			hsh
		end
	end
	
	def words
		input.split(/[^\w]/)
			 .map{|w| w.downcase.strip}
			 .reject &:empty?
	end
end
