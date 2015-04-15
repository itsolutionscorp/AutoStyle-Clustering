class Bob
  def hey(message)
    message.strip!

    if message.empty?
      return 'Fine. Be that way!'
    elsif message.upcase == message
      return 'Woah, chill out!'
    elsif message.end_with?('?')
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
