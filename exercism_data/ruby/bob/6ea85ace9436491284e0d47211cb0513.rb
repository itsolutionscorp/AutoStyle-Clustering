class Bob
  # @param [String] message
  # @return [String]
  def hey(message)
    case message
    when /\A[^a-z]*[A-Z]+[^a-z]*\Z/
      'Woah, chill out!'
    when /\?\Z/
      'Sure.'
    when /\A\s*\Z/
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
