class Bob
  def hey(text)
    @text = sanitize(text)

    case
      when silence?(@text)
        "Fine. Be that way!"
      when yelling?(@text)
        "Woah, chill out!"
      when question?(@text)
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
    text.empty?
  end

  def sanitize(text)
    text.to_s.strip
  end
end
