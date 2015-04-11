class Cipher
	attr_reader :key
	
	def initialize(key = nil)
		unless key
			alphabet = [*('a'..'z')]
			key = 100.times.map{ alphabet[rand(alphabet.length)] }.join
		end
		raise(ArgumentError) if key.empty? or key =~ /[^a-z]/
		@key = key
	end
	
	def encode(plaintext)
		code(plaintext, 1)
	end
	
	def decode(ciphertext)
		code(ciphertext, -1)
	end
	
	private 
	
	def code(text, direction)
		text.chars.zip(@key.chars.cycle).collect do |c, k| 
			a = 'a'.ord
			c = c.ord - a
			k = k.ord - a
			(a + (c + k * direction + 26) % 26).chr
		end.join
	end
end
