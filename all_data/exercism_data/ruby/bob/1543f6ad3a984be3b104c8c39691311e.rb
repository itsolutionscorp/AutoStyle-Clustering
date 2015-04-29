class Bob

  def hey(s)
    case
      when is_silence?(s)
        "Fine. Be that way!"
      when is_shouting?(s)
        "Woah, chill out!"
      when is_question?(s)
        "Sure."
      else
        "Whatever."
    end
  end

  protected

  def is_silence?(s)
    s.nil? || s.empty?
  end

  def is_shouting?(s)
    s == s.upcase
  end

  def is_question?(s)
    s.end_with?("?")
  end


end
