class Bob
	def hey(s)
		return "Fine. Be that way!" if s.gsub(/[\s]/, "").length==0
		return "Sure." if s.split("").last=="?" && s.gsub(/[A-Z?\s]/, "").length!=0
		return "Whoa, chill out!" if s.upcase==s && s.gsub(/[0-9,\s]/,"").length!=0
		return "Whatever."
	end
end
