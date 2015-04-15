class Bob

  def hey(sentence)
    case
      when silence?(sentence)
        "Fine. Be that way!"
      when question?(sentence)
        "Sure."
      when yell?(sentence)
        "Woah, chill out!"
      else
        "Whatever."
    end
  end

  private

  def silence?(sentence)
    sentence.nil? || sentence.empty?
  end

  def yell?(sentence)
    sentence == sentence.upcase
  end

  def question?(sentence)
    sentence[-1,1] == '?' && !yell?(sentence)
  end

end
