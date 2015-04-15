class Bob
  def hey(message)
    case message
    when /\A\s*\z/
      'Fine. Be that way!'
    when /\A[^a-z]+\z/
      'Woah, chill out!'
    when /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end
end
