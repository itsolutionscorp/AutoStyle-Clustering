class Bob
  def hey(message)
    message.strip!
    if message.empty?
      'Fine. Be that way!'
    elsif message == message.upcase && message.chars.any? { |c| c =~ /[A-z]/ }
      'Whoa, chill out!'
    elsif message[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
