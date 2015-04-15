class Raindrops
	def self.convert(number)
		str = ""
		values = {"3"=>"Pling", "5"=>"Plang", "7"=>"Plong"}
		values.keys.each do |v|
			if (number%v.to_i==0)
				str << values[v]
			end
		end

		if str==""
			str = number.to_s
		end
		return str
	end
end
