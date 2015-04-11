class Crypto

	def initialize(string)
		@string = string.downcase
	end
	
	def normalize_plaintext
		@string.gsub(/\W/, '').chomp
	end
	
	def size
		if 		Math.sqrt(self.normalize_plaintext.length) % 1 == 0
				Math.sqrt(self.normalize_plaintext.length)
		else	(Math.sqrt(self.normalize_plaintext.length) + 1).to_int
		end
	end
	
	def plaintext_segments
		self.normalize_plaintext.scan(/.{1,#{self.size}}/)
	end
	
	def ciphertext
		cipher_string = ''
		for i in 0..self.size
			self.plaintext_segments.each do |segment|
				unless segment[i] == nil
					cipher_string += segment[i] 
				end
			end		
		end
		cipher_string
	end
	
	def normalize_ciphertext
		normalized_cipher_string = ''
		i = 0
		self.ciphertext.split(//).each do |char|
			normalized_cipher_string += char
			i += 1
			if i % 5 == 0
				normalized_cipher_string += ' '
			end			
		end
		normalized_cipher_string.chomp(' ')
	end

end
