class Bob

  REPLIES = {
    question: 'Sure.',
    yell:     'Woah, chill out!',
    silent:   'Fine. Be that way!',
    misc:     'Whatever.',
  }

  def hey(str)
    response = find_response_type str
    REPLIES[response]
  end

private

  def find_response_type(str)
    return :silent if silent?(str)
    return :yell if yell?(str)
    return :question if question?(str)
    :misc
  end

  def question?(str)
    str.end_with? '?'
  end

  def silent?(str)
    str.strip.empty?
  end

  def yell?(str)
    str == str.upcase
  end

end
