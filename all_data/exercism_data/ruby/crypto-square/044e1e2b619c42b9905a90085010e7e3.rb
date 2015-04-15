class Crypto
	def initialize(message)
	
		@message = message
	end

	def normalize_plaintext
			# @message.gsub!(/\s[^a-z]/,"")
		  @message.gsub!(/[^\w]/,"")
		  # @message.gsub!(/[\s,]/,"")
		  @message.downcase!
		  return @message
	end

	def size
		# puts "MESSAGE"
		# print normalize_plaintext
		length = normalize_plaintext.size
		# print length
		low = 1
		high = length
		mid = high/2
		while mid*mid > length
			
			if mid*mid > length
	      high = mid
	     mid = (high+low)/2
			end
			if mid*mid < length
	      low = mid
	      mid = (high+low)/2
			end
		end
		# puts mid
   if mid*mid == length
   	return mid
   else
	 return mid + 1
	end
	end

	def plaintext_segments
		plaintext_segments = []
    count = 1
    string = ""
		normalize_plaintext.split("").each do |letter|
		  
		  # puts "SIZE"
		  # puts self.size  

		
		  if count%size != 0 
		  	 # print count
		  	 # puts size
		  	string = string + letter
		  	count = count + 1
		   
		else
			
		   string = string + letter
		    # puts count

		    # puts string
		  plaintext_segments << string
		  count = 1
			string = ""
		end
	  end
	  if string.size > 0
	  	plaintext_segments  << string
	  end
	  return plaintext_segments
	end

	def ciphertext
		ciphertext_in_array = plaintext_segments.dup
		
		plaintext_segments.each_index do |index|
      # size = plaintext_segments[0].size
       puts plaintext_segments[index]
      0.upto(size-1) do |s|
         
         puts plaintext_segments[index][s].to_s
       	 ciphertext_in_array[s][index] = plaintext_segments[index][s].to_s
      	

      end 
		end
		
		return ciphertext_in_array.join
	end

	def normalize_ciphertext
		# ciphertext_in_array = plaintext_segments.dup
		# print plaintext_segments
		# plaintext_segments.each_index do |index|
      
  #     0.upto(size-1) do |s|
  #        print s
  #     	 puts plaintext_segments[index][s].to_s
  #     	 ciphertext_in_array[s][index] = plaintext_segments[index][s].to_s
      	

  #     end 
		# end
	end
end
