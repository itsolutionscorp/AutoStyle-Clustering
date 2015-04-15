class Bob
  attr_reader :text

  def hey(text)
    @text = text
    if silence?
      'Fine. Be that way!'
    elsif shouting?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def silence?
    text.to_s.gsub(/^\s+/, '') == ''
  end

  def shouting?
    text.match /^[^a-z]+$/
  end

  def question?
    text.match /\?$/
  end
end
