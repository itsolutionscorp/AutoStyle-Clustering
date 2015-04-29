class Crypto
	def initialize(string) 
		@string = string.gsub(/[^a-zA-Z\d]/, "").downcase
	end
	
	def normalize_plaintext
		 @string
	end
	
	def size
		Math.sqrt(@string.length).ceil
	end
	
	def plaintext_segments
		length = self.size
		result = []
		@string.split(//).each_slice(length){|x| result << x.join}
		result
	end
	
	def ciphertext 
		plain = self.plaintext_segments
		length = self.size
		result = ""
		i = 0
		while i < length
			plain.each do |x|
				result << x[i] unless x[i] == nil
			end
			i += 1
		end
		result
	end
	
	def normalize_ciphertext 
		string = self.ciphertext
		length = Math.sqrt(string.length).round
		result = []
		string = self.ciphertext
		string.split(//).each_slice(length){|x| result << x.join}
		result.join(" ")
	end
end
