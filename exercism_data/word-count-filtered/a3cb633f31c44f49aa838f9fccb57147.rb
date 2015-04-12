class Phrase





	def initialize(phrase)
		@phrase = phrase	
	end

	def word_count
		count = Hash.new(0)
		@phrase.chars.map{ |x| x = x.match(/[[:word:]']/) ? x : " " }.join.split(" ").each do |word|
			count[word.downcase] += 1
		end
		count
	end
end
