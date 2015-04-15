def compute(str1, str2)


		arr1 = str1.scan(/\w/)
		arr2 = str2.scan(/\w/)
		count = 0


		while arr1.length > arr2.length
			arr1.delete_at(arr2.length)
		end


		arr1.each_with_index do |e,i|
			if e != arr2.fetch(i) then
				count += 1
			end
		end
		return count
	end