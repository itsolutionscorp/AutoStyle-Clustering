class Bob
  def hey(phase)
    case phase
    when /\A\s*\z/
      'Fine. Be that way!'
    when /\A[A-Z\W\d]+\z/
      'Woah, chill out!'
    when /\?\z/
      "Sure."
    else
      "Whatever."
    end
  end
end
