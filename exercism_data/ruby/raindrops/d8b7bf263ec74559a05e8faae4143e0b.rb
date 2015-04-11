class Raindrops

	def self.convert(max)

		output = ''

		1.upto(max) do |num|
			
			if max % num == 0 
				if num == 3
					output = output + 'Pling'
				elsif num == 5
					output = output + 'Plang'				
				elsif num == 7
					output = output + 'Plong'
				end		
			end
		end

		if output.empty?
			output = max.to_s
		end	

		output 
	end

end
