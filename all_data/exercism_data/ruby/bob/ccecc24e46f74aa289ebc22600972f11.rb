class Bob
	def hey(text)
		if text.blank? then return "Fine. Be that way!" end
		if text.caps? then return "Whoa, chill out!" end
		if text.question? then return "Sure." end
		return "Whatever."
	end
end

class String
	def caps?
		return true if self =~ /[A-Z]/ and self !~ /[a-z]/
	end

	def blank?
		return true if self.gsub(/\n/,'') =~ /^\s*$/
	end

	def question?
		return true if self[-1] == "?"
	end
end
