class Atbash

	ALPHABET = ('a'..'z').to_a
	INVALID_REG = /[^a-z0-9]/
	KEY = Hash[ALPHABET.zip ALPHABET.reverse]

	def self.encode(text)
		encoded = text.downcase.gsub(INVALID_REG, '').chars.map do |char| 
				ALPHABET.include?(char) ? KEY[char] : char
		end.join.scan(/.{1,5}/).join(' ')
	end

end

