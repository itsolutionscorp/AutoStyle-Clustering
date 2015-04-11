class Bob
  def hey(message)
    if message.strip.empty?
      return 'Fine. Be that way!'
    end
    if message == message.upcase
      return 'Woah, chill out!'
    end
    if message.end_with?('?')
      return 'Sure.'
    end
    'Whatever.'
  end
end
