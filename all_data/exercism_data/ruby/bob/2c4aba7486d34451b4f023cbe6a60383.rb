class Bob

def hey(test)
	if /[a-zA-Z]/.match(test) && test.upcase === test
		"Woah, chill out!"
	elsif /^ *\Z/.match(test)
		"Fine. Be that way!"
	elsif /\?\Z/.match(test)
		"Sure."
	else
		"Whatever."
	end
end

end
