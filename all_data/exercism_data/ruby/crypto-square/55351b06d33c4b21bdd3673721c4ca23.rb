class Crypto
	attr_reader :plaintext, :text, :ciphertext

	def initialize(text)
		@plaintext = text
		@text = normalize_plaintext
		encode
	end

	def normalize_plaintext
		plaintext.gsub(/\W/, '').downcase
	end

	def size
		Math.sqrt(text.size).ceil
	end

	def plaintext_segments
		text.scan /.{1,#{size}}/
	end

	def normalize_ciphertext
		ciphertext.scan(/.{1,#{(ciphertext.size/size)+1}}/).join(' ')
	end

	private

		def encode
			@ciphertext = characterMatrix.transpose.map { |r| r.join('') }.reduce(:<<)
		end

		def characterMatrix
			plaintext_segments.each_with_object([]) do |segment, matrix|
				matrix.push(character_row(segment))
			end
		end

		def character_row(segment)
			chars = segment.chars
			chars.push '' while chars.size < size
			chars
		end

end
