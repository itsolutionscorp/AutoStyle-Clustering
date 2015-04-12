def compute(strand1,strand2)
		result = 0
		if strand1 == strand2 then 0			
		elsif strand1.length != strand2.length then -1
		else
			for i in 0..strand1.length
				#puts "strand1[#{i}]: #{strand1[i]}\n"
				#puts "strand2[#{i}]: #{strand2[i]}\n"
				if strand1[i] != strand2[i] then result += 1#; puts "result: #{result}\n" 
				end
			end
		result
		end
	end