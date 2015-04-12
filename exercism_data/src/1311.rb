class Hamming
	
	def compute first, second
		longestString = first.length > second.length ? first : second
		shortestString = first.length > second.length ? second : first
		difference = 0
		shortestString.split("").each_with_index do |letter, index|
			difference += 1 if longestString[index] != shortestString[index]
		end
		difference
	end


end
