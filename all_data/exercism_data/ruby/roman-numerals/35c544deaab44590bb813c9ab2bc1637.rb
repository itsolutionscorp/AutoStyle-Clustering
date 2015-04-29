require "debugger"
class Integer
	def to_roman
		trans = {1 => "I", 5 => "V", 10 => "X", 50 => "L", 100 => "C", 500 => "D", 1000 => "M" }
		g_output = ""

		digits.each_with_index do |el, index|
			el = el.to_i
			range = 10 ** index
			output = ""
			if el < 4
				el.times {output += trans[range]}
			elsif el < 6
				(5 - el).times {output += trans[range]}
				output += trans[5*range]
			elsif el < 9
				output = trans[5*range]
				(el - 5).times {output += trans[range]}
			else
				(10 - el).times {output += trans[range]}
				output += trans[10*range]
			end
			g_output = output + g_output
		end
		g_output
	end

	def digits
		self.to_s.split("").reverse
	end

end
