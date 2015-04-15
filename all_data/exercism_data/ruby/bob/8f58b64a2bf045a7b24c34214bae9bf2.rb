class Bob

  def hey sentences
    if sentences.strip == ""
      return "Fine. Be that way!"
    elsif sentences == sentences.upcase
      return "Woah, chill out!"
    elsif sentences.strip[-1] == "?"
      return "Sure."
    else
      return "Whatever."
    end
  end
end
