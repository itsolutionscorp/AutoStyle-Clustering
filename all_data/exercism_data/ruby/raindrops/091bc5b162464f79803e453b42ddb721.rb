require 'prime'

class Raindrops
	def self.convert(number)
		
		p_factors = Prime.prime_division(number).flatten.uniq
		raindrops = ""
		p_factors.each do |p_factor| 
			case p_factor
			when 3
				raindrops << "Pling"
			when 5
				raindrops << "Plang"
			when 7
				raindrops << "Plong"
			end
		end
		raindrops = "#{number}" if raindrops.empty?
		raindrops
	end
end
