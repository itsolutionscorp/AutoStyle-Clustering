class Anagram
	def initialize(anagram)
		@anagram = anagram.downcase
	end


	def match(words)
		matches = Array.new
		words.each do |word|
      if word.downcase != @anagram
  			if word.downcase.chars.permutation.include?(@anagram.chars) then matches.push(word) end
      end
    end
		return matches
	end
end
