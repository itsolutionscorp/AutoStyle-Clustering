class Fixnum
	@@roman_keys = {
		1000 	=> "M",
		900		=> "CM",
		500 	=> "D",
		100 	=> "C",
		90 		=> "XC",
		50 		=> "L",
		10 		=> "X",
		9 		=> "IX",
		5 		=> "V",
		1 		=> "I"
	}
	def to_roman
		n = self
		roman_num = ""
		div, mod = n.divmod(@@roman_keys.keys[0])
		for i in 0..@@roman_keys.length-1 do
			div, mod = mod.divmod(@@roman_keys.keys[i]) unless i == 0
			current = @@roman_keys[@@roman_keys.keys[i]]
			nxt = @@roman_keys[@@roman_keys.keys[i+1]] unless i == @@roman_keys.length-1
			previous = @@roman_keys[@@roman_keys.keys[i-1]] unless i == 0
			case div
				when 6..8
					roman_num += nxt + (current*div)
				when 4
					roman_num += current + previous
				when 2..3
					roman_num += current*div
				when 1
					roman_num += current
			end
		end
		roman_num
	end
end
