class OCR
	# first step
	def initialize(text)
		@text = text
	end
	def convert
		result = []
		print @text.split("\n")
		@text.split("\n").each_slice(3).each do |a,b,c|
			if b.match(/\| \|/) && c.match(/\|_\|/)
				result << "0"
			elsif a == "" && b.match(/\s\s\|/) && c.match(/\s\|/)
				result << "1"
			elsif b.match(/\s_\|/) && c.match(/\|_/)
				result << "2"
			elsif b.match(/ _\|/) && c.match(/ _\|/)
				result << "3"
			elsif b.match(/\|_\|/) && c.match(/\s\|/)
				result << "4"
			elsif b == "|_" && c.match(/ _\|/)
				result << "5"
			elsif b == "|_" && c.match(/\|_\|/)
				result << "6"
			elsif a.match(/\s_/) && b.match(/ \|/)
				result << "7"
			elsif b.match(/\|_\|/) && c.match(/\|_\|/)
				result << "8"
			elsif b.match(/\|_\|/) && c.match(/ _\|/)
				result << "9"
			else 
				result << "?"
			end
		end
		result.reverse.join("")
	end
end
