class Bob
  def hey(message)
    # address without saying anything
    if message.strip.empty?
      'Fine. Be that way!'
    # yell at
    elsif message =~ /[A-Z]/ and message.upcase == message
      'Woah, chill out!'
    # ask a question
    elsif message.end_with?('?')
      'Sure.'
    # everything else
    else
      'Whatever.'
    end
  end
end
