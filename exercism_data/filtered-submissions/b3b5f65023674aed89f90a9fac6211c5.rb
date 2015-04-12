require 'pry'
class Hamming
	def compute(strand1, strand2)
		array1= strand1.chars
		array2= strand2.chars
		sum= 0
		counter= 0
		if array2.count - array1.count >= 0 
			array1.each do |letter|
				if letter != array2[counter]
					sum += 1
				end
				counter += 1
			end 
		else
			array2.each do |letter|
				if letter != array1[counter]
					sum += 1
				end
				counter += 1
			end
		end
		return sum
	end
end
