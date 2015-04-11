class Cipher
	def initialize(key)
		
		if key.nil?
			@key = ""
  	  @key = @key + ('a'..'z').to_a.shuffle.join
  	  @key = @key + ('a'..'z').to_a.shuffle.join
  	  @key = @key + ('a'..'z').to_a.shuffle.join
  	  @key = @key + ('a'..'z').to_a[0...4].shuffle.join
  	elsif key.match(/[A-Z]+/) || key.match(/[0-9]+/) || key.empty?
			raise ArgumentError
			
  	else
  		@key = key
  	end
  	
	end

  def key
  	
  	
    return @key
  end

  def encode(message)
  	encoded_string = ""
  	
  	message.split("").each_index do |index|
  		encoded_ordinal  = (message[index].ord + key[index].ord - 'a'.ord)
  		if encoded_ordinal > 122
  			encoded_ordinal = encoded_ordinal - 122 + 96
  		end
      encoded_letter = encoded_ordinal.chr
      encoded_string = encoded_string + encoded_letter
  	end
  	return encoded_string
  end

  def decode(message)
  	decoded_string = ""
  	
  	message.split("").each_index do |index|
  	  decoded_ordinal = (message[index].ord - key[index].ord + 'a'.ord)
  	   if decoded_ordinal < 97
  		 	decoded_ordinal = decoded_ordinal + 122 - 96
  		 end
      decoded_letter = decoded_ordinal.chr
      decoded_string = decoded_string + decoded_letter
  	end
  	return decoded_string
  end
end
