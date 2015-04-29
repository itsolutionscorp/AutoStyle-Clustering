class Anagram

	def initialize(word)
		@word = word
	end

	def match(array)
		match_array = []
		array.each do |x|
			next if x.downcase == @word.downcase
			match_array << x if x.downcase.split(//).sort == @word.downcase.split(//).sort
		end
		return match_array
	end
	
end
