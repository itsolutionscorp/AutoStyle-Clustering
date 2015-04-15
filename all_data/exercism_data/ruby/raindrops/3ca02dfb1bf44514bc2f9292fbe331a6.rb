require 'prime'


class Raindrops
	def self.convert(x)
		converted = ""
		if x % 3 == 0
			converted << "Pling"
		end
		if x % 5 == 0
			converted << "Plang"
		end
		if x % 7 == 0
			converted << "Plong"
		end
		if converted.length == 0
			converted << "#{x}"

		end
		#puts "#{x}"
		#puts "#{converted}"
		converted
	end

end

#r=Raindrops.new()
#r.convert(11)
