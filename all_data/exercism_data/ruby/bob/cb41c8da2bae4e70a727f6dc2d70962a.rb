class Bob
  def hey(text)
    case
      when silence?(text)
        "Fine. Be that way!"
      when yelling?(text)
        "Woah, chill out!"
      when question?(text)
        "Sure."
      else
        "Whatever."
    end
  end


  private

  def yelling?(text)
    text == text.upcase
  end

  def question?(text)
    text.end_with?("?")
  end

  def silence?(text)
    remove_whitespace(text.to_s).empty?
  end

  def remove_whitespace(text)
    text.strip
  end
end
