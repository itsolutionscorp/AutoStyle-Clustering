class Bob
	def hey(remark)
		@hi = remark.gsub(/\s+/, "")
		if @hi == ""
			return "Fine. Be that way!"

		elsif remark == remark.upcase && remark =~ /[[:alpha:]]/
	
				return 'Whoa, chill out!'
	

		elsif remark.end_with? "?"

			return 'Sure.'
		else
			return 'Whatever.'
		end
	end
end
