class Anagram < String

	def initialize(string)
	  super	
	end

	def match(list)
		list.select do |word|
			self.anagram?(word) 
		end
	end

	def anagram?(other)
    same_length   = other.length   == self.length 
		not_same_word = other.downcase != self.downcase

		if same_length and not_same_word
			self.downcase.each_char do |char| 
				other = other.downcase.sub(char, '')
			end
		end

		other.empty?
	end
end
