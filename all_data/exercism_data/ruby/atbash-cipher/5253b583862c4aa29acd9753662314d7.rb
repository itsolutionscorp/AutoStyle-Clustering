class Atbash

	def self.encode(message)
		message.downcase.chars.map { |c| 
			encode_char(c)
		}	
			.compact
			.each_slice(5)
			.join(" ")
	end

	private

		def self.encode_char(c)
			case c.ord
			when 97..(97+26) then reverse(c) #letter
			when 48..(48+10) then c 		 #digit
			else				  nil		 #other
			end
		end

		def self.reverse(letter)
			(97+26-(letter.ord - 97+1)).chr
		end

end
