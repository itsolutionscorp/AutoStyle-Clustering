class Bob
	def hey(remark)
		remark.strip!
		return "Fine. Be that way!" if silent?(remark)
		return "Whoa, chill out!" if yelling?(remark)
		return "Sure." if question?(remark)
		"Whatever."
	end

	def yelling?(remark)
		remark == remark.upcase && remark.match(/[[:alpha:]]/)
	end

	def question?(remark)
		remark.end_with?('?')
	end

	def silent?(remark)
		remark.empty?
	end
end
