require 'pry'

class Hamming

	def compute(string1, string2)
		string1.length >= string2.length ? string1 = string1[0...string2.length] : string2 = string2[0...string1.length]
		sum, y = 0, 0
		string1.chars.each { |char|	char == string2.chars[y] ? (sum += 1; y += 1): y += 1 }
		string1.length - sum
	end
end
