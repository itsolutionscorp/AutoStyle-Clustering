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
    dialogue.end_with?('?')
  end

  def shout?(dialogue)
    dialogue.match(/[[:upper:]]/) && dialogue == dialogue.upcase
  end

  def silence?(dialogue)
    dialogue.strip.empty?
  end

end
