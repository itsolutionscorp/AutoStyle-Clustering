class Hamming

	def self.compute firstDna, secondDna
		fir = firstDna.split("")
		sec =  secondDna.split("")
		hamming_distance fir, sec
	end
	def  self.hamming_distance first,second
		hamming_distance_count = 0
		minimun = get_min(first.count, second.count) - 1
		for idx in 0..minimun
			hamming_distance_count+= 1 if first[idx] != second[idx] 
		end
		hamming_distance_count
	end
	def self.get_min(*values)
	 return values[0] if values[0] == values[1]
	 values.min
	end
end
