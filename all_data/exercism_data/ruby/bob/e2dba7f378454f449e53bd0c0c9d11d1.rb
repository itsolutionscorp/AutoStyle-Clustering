class Bob
	def hey(remark)
		remark.strip!
		return "Fine. Be that way!" if remark.empty?
		return "Whoa, chill out!" if yelling?(remark)
		return "Sure." if remark.end_with?('?')
		"Whatever."
	end

	def yelling?(string)
		string == string.upcase && string.match(/[[:alpha:]]/)
	end

end
