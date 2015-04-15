class Crypto
	def initialize(text)
		@raw_text = text
		@clean_text = text.gsub(/[^[:alnum:]]/, "").downcase()
	end

	def normalize_plaintext()
		@clean_text
	end

	def size()
		Math.sqrt(@clean_text.length).ceil()
	end

	def plaintext_segments()
		0.step(@clean_text.length - 1, size).collect{|i| @clean_text[i...i + size]}
	end

	def ciphertext()
		groups = Array.new(size){|i| []}
		plaintext_segments().each do |text| 
			text.chars.each_with_index{|c,i| groups[i] << c}
		end
		groups.join()
	end

	def normalize_ciphertext()
		text = ciphertext()
		0.step(text.length - 1, 5).collect{|i| text[i...i + 5]}.join(' ')
	end
end
