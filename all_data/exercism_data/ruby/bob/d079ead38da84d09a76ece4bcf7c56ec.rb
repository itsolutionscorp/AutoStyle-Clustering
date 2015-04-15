class Bob
  def hey(message)
    if nothing?(message)
      'Fine. Be that way!'
    elsif shouting?(message)
      'Woah, chill out!'
    elsif question?(message)
      'Sure.'
    else
      'Whatever.'
    end
  end

private

  def nothing?(message)
    message.strip.empty?
  end

  def shouting?(message)
    all_caps?(message) && contains_letters?(message)
  end

  def question?(message)
    message =~ /\?\z/
  end

  def all_caps?(message)
    message.upcase == message
  end

  def contains_letters?(message)
    message =~ /[a-z]/i
  end
end
