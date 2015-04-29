class Bob

  def hey(dialogue)
    if shout?(dialogue)
      "Woah, chill out!"
    elsif question?(dialogue)
      "Sure."
    elsif silence?(dialogue)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def question?(dialogue)
    dialogue =~ /\?\z/
  end

  def shout?(dialogue)
    dialogue =~ /[A-Z]/ && dialogue == dialogue.upcase
  end

  def silence?(dialogue)
    dialogue.strip.length == 0
  end

end
