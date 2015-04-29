class Bob
  def hey message
    if message.strip.empty?
      return 'Fine. Be that way!'
    end
    # I can check upcase with: 'unless message.upcase!',
    # but I think change message object bad idea.
    if message.eql? message.upcase 
      return 'Woah, chill out!'
    end
    if message.end_with? '?'
	return 'Sure.'
    end 
    'Whatever.'
  end
end
