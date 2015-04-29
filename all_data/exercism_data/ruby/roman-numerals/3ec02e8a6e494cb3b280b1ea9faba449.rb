class Numeral
	Phases = [ "I", "V", "X", "L", "C", "D", "M"]
	def self.transform(num)
		_num = num
		phase = 0
		roman = ""
		while _num != 0
			if _num%10 != 9 
				if _num%10 != 4
					for i in 0..._num%5
						roman.concat(Phases[phase])
					end
				else
					roman.concat(Phases[phase+1]).concat(Phases[phase])
				end
			_num -=_num%5
			roman.concat(Phases[phase+1]) if _num%10 == 5
			else
				roman.concat(Phases[phase+2]).concat(Phases[phase])
			end
			phase += 2
			_num /= 10
		end
		roman.reverse!
		return roman
	end
end

public
def to_roman
	return Numeral.transform(self)
end
