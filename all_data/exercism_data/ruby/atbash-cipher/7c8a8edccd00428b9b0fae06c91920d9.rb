class Atbash
	def self.encode(message)
		encoded_message = ""
		message.gsub!(/\s/,"")
		message.gsub!(/,/,"")
		message.gsub!(/\./,"")
		count = 0
		# if message[-1] == "."
		#   	message = message[0..-2]
		# end

    

		message.downcase.split('').each do |letter|
			if count%5 == 0 && count != 0
				encoded_message = encoded_message + " "
			end

			count = count + 1

			if (48..57).to_a.include? letter.ord
					print letter
			   encoded_letter = letter
			else
				 encoded_letter = ("z".ord - letter.ord + "a".ord).chr
				
			end
			encoded_message = encoded_message + encoded_letter
		end
		return encoded_message
	end
end
