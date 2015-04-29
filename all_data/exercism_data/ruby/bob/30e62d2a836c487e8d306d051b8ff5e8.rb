class Bob
  def hey(message)
    if message.strip.empty?
      'Fine. Be that way!'
    elsif message == message.upcase && has_letters?(message)
      'Woah, chill out!'
    elsif message.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private
  def has_letters?(string)
    !string.gsub(/[^a-z]/i, '').empty?
  end
end
