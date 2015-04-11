class Bob
  def hey(input)
  	case 
  	when input =~ /[[:alpha:]]/ && input == input.upcase
			'Woah, chill out!'
  	when input[-1] == '?'
			'Sure.'
  	when input =~ /^\s*$/ && input !~ /[[:alpha:]]/
			'Fine. Be that way!'
  	else
			'Whatever.'
  	end
	end
end
