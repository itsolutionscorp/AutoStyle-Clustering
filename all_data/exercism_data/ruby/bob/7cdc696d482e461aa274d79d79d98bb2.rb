class Bob

	def hey(remark)
		return "Fine. Be that way!" if no_remark?(remark)
		return "Whoa, chill out!" if yelling?(remark)
		return "Sure." unless question?(remark)
		return "Whatever."
	end

	def no_remark?(remark)
		remark.strip.empty?
	end

	def yelling?(remark)
		!remark.scan(/[A-Z]{2,}/).empty? && remark.scan(/[a-z]/).empty?
	end

	def question?(remark)
		remark.scan(/\?\z/).empty?
	end

end
