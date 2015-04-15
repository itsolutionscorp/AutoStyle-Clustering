class Bob
  def hey(text)
    @text = text
    return 'Fine. Be that way!' if not_talking?
    return 'Woah, chill out!' if yelling?
    return 'Sure.' if questioning?
    return 'Whatever.'
  end

  private

  def is_not_talking?
    !@text || @text.length == 0
  end

  def is_yelling?
    @text == @text.upcase
  end

  def is_questioning?
    @text.strip[-1] == "?"
  end
end
