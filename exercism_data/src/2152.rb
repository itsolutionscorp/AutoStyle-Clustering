class Hamming
	def compute(strand1,strand2)
		array1 = strand1.split(//)
		array2 = strand2.split(//)
		differences = 0
		
		
		array1.each_with_index do |item, pos|
			unless array1[pos] == array2[pos]
				differences += 1
			end
			
		end
		
		differences
	end
end
