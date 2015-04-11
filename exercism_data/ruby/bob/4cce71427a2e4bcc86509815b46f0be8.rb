class Bob
  def hey message
    message = message.to_s.strip
    if message.empty?
      'Fine. Be that way!'
    elsif message !~ /[a-z]/
      'Woah, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
