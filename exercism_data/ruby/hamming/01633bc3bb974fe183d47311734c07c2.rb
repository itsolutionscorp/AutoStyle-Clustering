class Hamming
	def self.compute(data1, data2)
		if data1.length != data2.length
			raise 'data1 must be the same length as data2 to compute Hamming distance'
		end
		
		$distance = 0

		for i in 0..data1.length do
			if data1[i] != data2[i]
				$distance += 1
			end
		end

		return $distance
	end
end
