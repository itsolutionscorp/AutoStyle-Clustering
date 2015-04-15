require 'Prime'
class Raindrops
	def self.convert(numb)
		rain = ''
		facts = Prime.prime_division(numb)
		facts.each do |fact|
			if (fact[0] == 3) then rain += "Pling" end
			if (fact[0] == 5) then rain += "Plang" end
			if (fact[0] == 7) then rain += "Plong" end
		end
		if (rain == '') then rain = numb.to_s end
		return rain
	end
end
