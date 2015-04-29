class Bob
  def hey(text)
    @text = sanitize(text)

    case
      when silence?
        "Fine. Be that way!"
      when yelling?
        "Woah, chill out!"
      when question?
        "Sure."
      else
        "Whatever."
    end
  end


  private


  def question?
    @text.end_with?("?")
  end

  def sanitize(text)
    text.to_s.strip
  end

  def silence?
    @text.empty?
  end

  def yelling?
    @text == @text.upcase
  end
end
