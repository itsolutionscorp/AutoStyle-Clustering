class Bob
  def hey(message)
    return 'Fine. Be that way!' if message.delete(' ').empty?
    return 'Woah, chill out!' if message == message.upcase && message.downcase.delete('a-z') != message.downcase
    return 'Sure.' if message[-1] == '?'
    return 'Whatever.'
  end
end
