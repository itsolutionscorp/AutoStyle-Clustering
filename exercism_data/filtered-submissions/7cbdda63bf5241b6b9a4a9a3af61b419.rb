def compute (string1, string2)
		count = 0
		array1 = string1.chars
		array2 = string2.chars

		n = array1.length

		n.times do |t|
			unless array2[t] == array1[t]
				count += 1
			end
		end

		return count

	end