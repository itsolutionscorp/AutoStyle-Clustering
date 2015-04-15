class Bob
  def hey string_input
  	case
	  	when string_input.strip.length.zero? then 'Fine. Be that way!'
	  	when /^[A-Z0-9,%@#$\(*^.?!\s+]*[(.?!\s+)]/.match(string_input.strip) then 'Woah, chill out!'
		when string_input.split(//).last == '?' then 'Sure.'
	  	else 'Whatever.'
  	end
  end
end
