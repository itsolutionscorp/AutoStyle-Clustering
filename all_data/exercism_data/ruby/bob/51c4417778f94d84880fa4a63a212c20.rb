class Bob

  def hey(message)
    if message =~ /\A\s*\z/
      'Fine. Be that way!'
    elsif message !~ /[a-z]/ && message =~ /[A-Z]/
      'Woah, chill out!'
    elsif message =~ /\?\z/
      'Sure.'
    else
      'Whatever.'
    end
  end

end
