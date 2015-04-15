class Bob
  def hey message
  	case
	  	when message.strip.empty? then 'Fine. Be that way!'
	  	when message.upcase!.nil? then 'Woah, chill out!'
		when message.end_with?('?') then 'Sure.'
	  	else 'Whatever.'
  	end
  end
end
