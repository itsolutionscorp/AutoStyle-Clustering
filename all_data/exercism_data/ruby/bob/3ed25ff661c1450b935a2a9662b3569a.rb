class Bob
  def hey(text)
    @text = text
    return 'Fine. Be that way!' if silence?
    return 'Whoa, chill out!' if roar?
    return 'Sure.' if question?
    'Whatever.'
  end

  private

  def silence?
    @text.strip.empty?
  end

  def question?
    @text.end_with? '?'
  end

  def roar?
    @text[/[[:alpha:]]/] && @text == @text.upcase
  end
end
