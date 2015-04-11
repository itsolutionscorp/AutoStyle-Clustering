class Anagram

	def initialize(word)
		#detector = s.split('').shuffle(s.length()).join
		@word = word
	end

	def match(words)
		words.select do |word|
			word.split("") == @word.split("")
		end
end

#The split method helps us begin scrambling but, 
# the letters are dis organized
