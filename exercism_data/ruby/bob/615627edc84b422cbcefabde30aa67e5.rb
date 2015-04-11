class Bob

  def hey(message = nil)
    case type_of message
    when :question then 'Sure.'
    when :yelling  then 'Woah, chill out!'
    when :empty    then 'Fine. Be that way!'
    else "Whatever."
    end
  end

  def type_of(message)
    return :empty if message.nil? || message.strip == ''
    return :yelling if message.upcase == message
    return :question if message[-1] == '?'
  end
end
