class Bob
  def hey(words)
    if words[-1] == '?'
	  'Sure.'
	elsif words.upcase == words
	  'Woah, chill out!'
	elsif words[/\w+/]
	  'Whatever.'
	else
	  'Fine. Be that way!'
	end
end
