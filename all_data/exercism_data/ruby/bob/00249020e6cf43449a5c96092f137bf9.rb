class Bob
  attr_reader :listened

  def hey(message)
    @listened = message
    if nothing?
      "Fine. Be that way!"
    elsif shouting?
      "Woah, chill out!"
    elsif questioning?
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def nothing?
    listened.strip.empty?
  end

  def shouting?
    listened == listened.upcase
  end

  def questioning?
    listened.end_with?('?')
  end
end
