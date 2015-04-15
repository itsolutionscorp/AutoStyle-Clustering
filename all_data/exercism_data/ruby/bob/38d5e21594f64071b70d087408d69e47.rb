class Bob
  def hey message
    message ||= ''
    if message == ''
      'Fine. Be that way!'
    elsif message == message.upcase
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      "Whatever."
    end
  end
end
