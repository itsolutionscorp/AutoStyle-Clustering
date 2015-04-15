class Bob

  REPLIES = {
    question: 'Sure.',
    yell:     'Woah, chill out!',
    silent:   'Fine. Be that way!',
    misc:     'Whatever.',
  }

  def hey str
    REPLIES[input_type str]
  end

private

  def input_type str
    return :silent if is_silent?(str)
    return :yell if is_yell?(str)
    return :question if is_question?(str)
    :misc
  end

  def is_question? str
    str.match(/\?\Z/)
  end

  def is_silent? str
    str.match(/\A\s*\Z/)
  end

  def is_yell? str
    str.match(/\A[^a-z]+\Z/)
  end

end
