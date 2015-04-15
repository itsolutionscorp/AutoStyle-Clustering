class Bob
  def hey message
    if message.strip.empty?
      return 'Fine. Be that way!'
    end
    if message.eql? message.upcase # I can check upcase with: 'unless message.upcase!' - but I think change message object bad idea.
      return 'Woah, chill out!'
    end
    if message.end_with? '?'
	return 'Sure.'
    end 
    'Whatever.'
  end
end
