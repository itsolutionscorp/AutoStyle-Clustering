class Cipher
	MIN_GEN_KEY_LEN = 100
	LETTERS = [*?a..?z]

	attr_reader :key

	def initialize(key = nil)
		@key = key || generate_key
		raise ArgumentError unless valid?(@key)
	end
	
	def encode(plaintext)
		transform(plaintext, :+)
	end
	
	def decode(cipher)
		transform(cipher, :-)
	end
	
private

	def valid?(key)
		!key.empty? && (key.gsub(/[^a-z]/, "") == key)
	end
	
	def generate_key
		length = MIN_GEN_KEY_LEN + rand(MIN_GEN_KEY_LEN)
		[*0...length].inject("") { |k, i| k + LETTERS.sample }
	end
	
	def transform(text, op)
		text.chars.map.with_index do |char, idx|
			new_idx = [LETTERS.index(char), 
								 LETTERS.index(key[idx])].reduce(op) % LETTERS.length
			LETTERS[new_idx]
		end.join
	end
end
