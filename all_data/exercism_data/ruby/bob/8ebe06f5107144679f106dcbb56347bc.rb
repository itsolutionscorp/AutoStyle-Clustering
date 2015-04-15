class Bob

	def hey(remark)

		if remark.strip.empty?
			p "Fine. Be that way!"

		elsif remark == remark.upcase && remark =~ /[a-zA-Z]/
			p "Whoa, chill out!"

		elsif remark.split('').last == '?'
			p "Sure."

		else
			p "Whatever."
		end
	end
end
