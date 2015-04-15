def teen_bob(str)
	if str[-1] == "?"
		return 'Sure.'
	elsif str == str.upcase && str.length > 1
		return 'Woah, chill out!'
	elsif str.length < 2
		return 'Fine. Be that way!'
	else
		return 'Whatever.'
	end
end
