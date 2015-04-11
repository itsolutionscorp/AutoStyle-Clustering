class Bob
  def hey(str)
    case
	  when str.strip == ''
	    'Fine. Be that way!'
	  when str == str.upcase && /[a-zA-Z]/.match(str)
	    'Woah, chill out!'
	  when str[-1] == '?'
	    'Sure.'
	  else 'Whatever.'
	end
  end
end
