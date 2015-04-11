class PigLatin
	def self.translate(string)
		array = string.split(" ")
		result = ""
		array.each do |str|
			if str.match(/^[aeiou]/)
				result << str + "ay"
			elsif match = str.match(/\A(qu)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A([^aeiou]qu)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A(thr)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A(th)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A(sch)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A(yt)([a-z]*)/i)
				result << str + "ay"
			elsif match = str.match(/\A(y)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A(xr)([a-z]*)/i)
				result << str + "ay"
			elsif match = str.match(/\A(x)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
			elsif match = str.match(/\A([^aeiou]*)([a-z]*)/i)
				first, second = match.captures
				result << second + first + "ay"
		end
		result << " " 
		end
		result.strip!
	end
end
