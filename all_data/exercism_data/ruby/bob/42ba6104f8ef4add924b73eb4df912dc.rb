class Bob
  def hey(message)
    # address without saying anything
    if message.match(/^\s*\Z/)
      'Fine. Be that way!'
    # yell at
    elsif message.match(/[A-Z]/) and message.upcase == message
      'Woah, chill out!'
    # ask a question
    elsif message.match(/\?\Z/)
      'Sure.'
    # everything else
    else
      'Whatever.'
    end
  end
end
