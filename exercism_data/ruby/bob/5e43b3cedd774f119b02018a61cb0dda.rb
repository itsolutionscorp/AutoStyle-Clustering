class Bob
  def hey(speaker)
    case
      when is_silent?(speaker)
        "Fine. Be that way!"
      when is_yelling?(speaker)
        "Woah, chill out!"
      when is_question?(speaker)
        "Sure."
      else
        "Whatever."
    end
  end

  private
  def is_yelling?(speaker)
    speaker.upcase == speaker && speaker =~ /[A-Z]/
  end

  def is_question?(speaker)
    speaker.end_with?("?")
  end

  def is_silent?(speaker)
    speaker.strip == ""
  end
end
