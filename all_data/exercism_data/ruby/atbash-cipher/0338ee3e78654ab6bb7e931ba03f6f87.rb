class Atbash

	CIPHER = ('a'..'z').to_a

	def self.encode(string)
		newstring = ''
		string = string.gsub(/[^\w]/, '').downcase
		string.each_char do |char|
			if char.match(/[a-z]/)
				newstring << CIPHER[ -(CIPHER.index(char) + 1) ]
			else
				newstring << char
			end
		end

		x=5
		while x < newstring.length
			newstring.insert(x, " ")
			x += 6
		end

		return newstring
	end

end
