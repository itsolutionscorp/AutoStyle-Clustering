class Hamming

	def compute(strand1,strand2)
		
		array = (strand1.split("")).zip(strand2.split(""))

		distance = 0
		array.each do |compare|
			if compare[0] == compare[1] or compare[1] == nil
				distance = distance + 0
			elsif compare[0] != compare[1]
				distance = distance + 1
			end
		end

		return distance

	end

end
