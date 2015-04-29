class Bob

  def hey(message)
    case message
    when /\A\ *\z/
      'Fine. Be that way!'
    when /\A[^a-z]+\z/
      'Woah, chill out!'
    when /[^\?]\z/
      'Whatever.'
    when /\?\z/
      'Sure.'
    end
  end

end
