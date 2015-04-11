class String
	def anagram_of?(other)
		(self.downcase != other.downcase) && same_letters(self, other)
	end
	
private
	def same_letters(s1, s2)
		s1.downcase.chars.sort == s2.downcase.chars.sort
	end
end

class Anagram
	def initialize(word)
		@word = word
	end
	
	def match(candidates)
		candidates.select { |candidate| candidate.anagram_of?(@word) }
	end
end
