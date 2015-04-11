class Bob
  def hey(words)
    case
      when shouting?(words)
        "Woah, chill out!"
      when question?(words)
        "Sure."
      when silence?(words)
        "Fine. Be that way!"
      else
        "Whatever."
    end
  end


  private
  def shouting?(words)
    words == words.upcase && words.match(/[A-Z]/)
  end

  def question?(words)
    words.end_with?("?")
  end

  def silence?(words)
    words.strip.empty?
  end

end
