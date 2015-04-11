class Anagram < String

	def initialize(string)
	  super	
	end

	def match(list)
		list.select do |anagram|
			if anagram.length == self.length and anagram.downcase != self.downcase
				self.downcase.each_char {|c| anagram = anagram.downcase.sub(c, '')}
				anagram.empty?
			end
		end
	end
end
