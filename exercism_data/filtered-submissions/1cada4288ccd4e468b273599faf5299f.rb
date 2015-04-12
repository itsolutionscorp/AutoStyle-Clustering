module Hamming
	extend self

	def compute (*strings)
		distance = 0
		length = case strings.first <=> strings.last
						 when 1 then strings.last.length 
						 else strings.first.length
						 end

		length.times do |idx| 
			distance += 1 if strings.first[idx] != strings.last[idx]
		end
		
		distance
	end

end
