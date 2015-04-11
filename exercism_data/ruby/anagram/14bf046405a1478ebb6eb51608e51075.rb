class Anagram 
	def initialize(string)
		@string = string 
	end
	
	def match(array)
		original = @string.split(//).map{|e| e.downcase}.sort
		array.select do |word|
			if word.size == @string.size
				contents = word.split(//).map{|e| e.downcase }.sort
				word if contents == original && word.downcase != @string.downcase
			end
		end
	end
end
