class Crypto
	attr_reader :text, :ciphertext

	def initialize(text)
		@plaintext = text
		normalize_plaintext
	end

	def normalize_plaintext
		@text ||= @plaintext.gsub(/\W/, '').downcase
	end

	def size
		@size ||= Math.sqrt(text.size).ceil
	end

	def plaintext_segments
		text.scan /.{1,#{size}}/
	end

	def normalize_ciphertext
		ciphertext.scan(/.{1,#{(ciphertext.size/size)+1}}/).join(' ')
	end

	def ciphertext
		@ciphertext ||= characterMatrix.transpose.map { |r| r.join }.join.delete(' ')
	end

	private

		def characterMatrix
			plaintext_segments.each_with_object([]) do |segment, matrix|
				matrix.push(segment.ljust(size).chars)
			end
		end

end
