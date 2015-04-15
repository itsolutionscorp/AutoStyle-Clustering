LETTERS = [*('a'..'z')]
NUMBERS = [*('0'..'9')]
RING = Hash[(LETTERS + NUMBERS).zip(LETTERS.reverse() + NUMBERS)]

class Atbash
	def self.encode(text)
		text = text.gsub(/[^[:alnum:]]/, "").downcase() #clean
		text = text.chars.collect{|c| RING[c]}.join  #encode
		0.step(text.length - 1, 5).collect{|i| text[i...i + 5]}.join(' ') #segment
	end
end
