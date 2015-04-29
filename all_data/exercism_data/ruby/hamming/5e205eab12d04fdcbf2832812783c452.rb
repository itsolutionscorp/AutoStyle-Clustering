class Hamming

	# must be class method for hamming_test.rb
	def self.compute(str1, str2) 

		# returns an array for each string from a regexp
		arr1 = str1.scan(/\w/) 
		arr2 = str2.scan(/\w/)
		count = 0
		
		# protection from array out of bounds, destroys arr1 beyond index arr2.length
		while arr1.length > arr2.length
			arr1.delete_at(arr2.length)
		end

		# iterates the count if element e from arr1 and element i from arr2 do not match
		arr1.each_with_index do |e,i|
			if e != arr2.fetch(i) then 
				count += 1
			end
		end
		return count
	end

end
