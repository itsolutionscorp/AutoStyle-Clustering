module Atbash
	LETTERS = [*?a..?z]
	CODE = LETTERS.zip(LETTERS.reverse).to_h
	
	def self.encode(text)
		text.downcase.scan(/[a-z0-9]/).map do |char|
			CODE[char] || char
		end.each_slice(5).to_a.map(&:join).join(" ")
	end
end
