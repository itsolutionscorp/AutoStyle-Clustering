class Crypto

	def initialize(message)
		@message = message.gsub(/[^0-9a-zA-Z]/, '').downcase
	end

	def normalize_plaintext
		@message
	end

	def size
		Math.sqrt(@message.size).ceil
	end

	def plaintext_segments
		segments = @message.chars.each_slice(self.size).map(&:join)
	end

	def ciphertext
		ciphered = String.new
		pos = 0
		finish = self.size
		text = plaintext_segments

		while pos <= finish
			text.each do |segment|
				ciphered += segment[pos] if segment[pos]
			end
			pos += 1
		end
		ciphered
	end

	def normalize_ciphertext
		ciphertext.chars.each_slice(5).map(&:join).join(' ')
		# ciphered_text = ciphertext
		# result = Array.new
		# ciphered_text.chars.each_slice(5) do |slice|
		# 	result << slice.join
		# end
		# result.join(' ')
	end

end
