class Bob
  def hey(message)
    if message.empty?
      'Fine. Be that way!'
    elsif message !~ /[a-z]/ && message =~ /[A-Z]/
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
