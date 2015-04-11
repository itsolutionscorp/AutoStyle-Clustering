class Bob
  def hey message
    message ||= ''
    message.strip!

    if message == ''
      'Fine. Be that way.'
    elsif message == message.upcase
      'Woah, chill out!'
    elsif message =~ /\?$/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
