class Bob

def hey(spoken)
	# we'll take anything that can go to a string
	return whack(spoken.to_s.strip)
end

private

# just dealing with a stripped string
def whack(uttered)

	if uttered.empty?
	then	return 'Fine. Be that way!'
	elsif (uttered =~ /[a-zA-Z]/) && (uttered.upcase == uttered)
	then	return 'Woah, chill out!'
	elsif uttered.end_with?('?')
	then	return 'Sure.'
	end
	return 'Whatever.'
end

end
