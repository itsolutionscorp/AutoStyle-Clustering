require 'prime'

class Raindrops
	def self.convert(num)
    factors = Prime.prime_division(num).to_s

		string = ""

  	(0..factors.length).each do |i|
			case factors[i]
				when '3'
					string << "Pling"
				when '5'
					string << "Plang"
				when '7'
					string << "Plong"
			end
		end

		if string == ""
			string << num.to_s
		end

    return string
  end
end
