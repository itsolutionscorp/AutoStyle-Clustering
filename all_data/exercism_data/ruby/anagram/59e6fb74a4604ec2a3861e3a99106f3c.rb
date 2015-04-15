class Anagram
	def initialize(input)
		@word = input
	end
	def match(list)
		result = []
		list.each do |item|
			if item.length == @word.length
				if ((item.downcase.chars.sort.join == @word.downcase.chars.sort.join)&&(item.downcase() != @word.downcase()))
					result.push(item)
				end
			end
		end	
		return result	
	end
end
