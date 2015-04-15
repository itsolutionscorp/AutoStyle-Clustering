class String
	def chunks(max_size)
		self.scan(/.{1,#{max_size}}/)
	end
end

class Crypto
	CIPHER_CHUNK_SIZE = 5
	
	def initialize(plain_text)
		@plain_text = plain_text
		@normalized = @plain_text.scan(/[0-9A-Za-z]/).join.downcase
	end
	
	def normalize_plaintext
		@normalized
	end
	
	def size
		Math.sqrt(@normalized.length).ceil
	end
	
	def plaintext_segments
		@normalized.chunks(size)
	end
	
	def ciphertext
		plaintext_segments.map(&:chars).collect do |segment|
			segment.fill(nil, segment.length, size-segment.length)
		end.transpose.join
	end
	
	def normalize_ciphertext
		ciphertext.chunks(CIPHER_CHUNK_SIZE).join(" ")
	end
end
