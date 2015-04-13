def compute(first,second)
		diff=0
		if first.length==second.length
			first.length.times do |i|

				unless first[i]==second[i]


					diff=diff+1
				end
			end
		else
			if first.length>second.length
				diff=1
			else
				diff=2
			end

		end
		diff
	end