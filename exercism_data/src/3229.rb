 class Hamming

 	def compute(x, y)
 		count = 0 		
 		string1 = x.chars
 		string2 = y.chars
 		# until string2[-1] >> this returns nil for everything.
	 		string1.each_with_index do |letter, index| 		
	 			# until index = -1 >>> This makes the method RETURN the content of the strings instead of the COUNT.
	 			# index = 0
		 			if string2[0..-1] != string1[0..-1]
		 				count += 1
		 				# index += 1
		 			end
	 			# index += 1
	 			end		 			
	 		# end
	 		count
 	end

 end
