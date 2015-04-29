class Bob

	def hey(text)
		if shouting(text)
			"Woah, chill out!"
		elsif text.end_with?("?")
			"Sure."
		elsif text.empty? || text.tr(" ", "").empty?
			"Fine. Be that way!"
		else
			"Whatever."
		end
	end

	private
	def shouting(text)
		text == text.upcase && text.tr(" ,123456789?", "").empty? == false
	end

end
