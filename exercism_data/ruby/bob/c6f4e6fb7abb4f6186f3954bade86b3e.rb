class Bob
  def hey(text)
    @text = text.strip
    return 'Fine. Be that way!' if is_not_talking?
    return 'Woah, chill out!' if is_yelling?
    return 'Sure.' if is_questioning?
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
    @text[-1] == "?"
  end
end
