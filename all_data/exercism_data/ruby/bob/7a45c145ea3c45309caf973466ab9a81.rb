class Bob
  def hey message
    if message.strip.empty?
      'Fine. Be that way!'
    elsif message.upcase == message && message =~ /[A-Z]+/
      'Woah, chill out!'
    elsif message =~ /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
