class Bob
  def hey(message)
    if message.to_s.match(/^\s*$/)
      # Any amount of whitespace and nothing else
      'Fine. Be that way!'
    elsif message.match(/[A-Z]{2,}([^\.\s]+)$/)
      # Two or more caps in a row, followed by at least 1 character that isn't
      # a full stop or a space
      'Woah, chill out!'
    elsif message.match(/\?$/)
      # Ending with a question mark
      'Sure.'
    else
      'Whatever.'
    end
  end
end
