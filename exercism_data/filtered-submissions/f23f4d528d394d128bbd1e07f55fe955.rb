class Hamming
	def compute(first, second)
		shorter = ([first,second].min_by { |x| x.length }).length
		
		hamming_count = 0
		(0..(shorter-1)).each do |i|
			if first.slice(i) != second.slice(i) then
				hamming_count = hamming_count + 1
			end
		end

		hamming_count
	end
end
