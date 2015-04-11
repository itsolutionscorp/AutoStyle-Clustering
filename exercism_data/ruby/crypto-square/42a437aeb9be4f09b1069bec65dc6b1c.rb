class Crypto
	attr_reader :input, :size
	def initialize (input)
		@input = input
		@size = input_size
	end

	def normalize_plaintext
		input.downcase.gsub(/[^a-z0-9]/, '')
	end

	def plaintext_segments
		normalize_plaintext.scan(/.{1,#{size}}/)
	end

	def ciphertext
		(0...size).inject("") do |cipher, index|
			plaintext_segments.each do |x|
				cipher += x[index] if x[index]
			end
			cipher
		end
	end

	def normalize_ciphertext
		ciphertext.scan(/.{1,5}/).join ' '
	end

	private
		def input_size
			(Math.sqrt normalize_plaintext.length).ceil
		end
end
