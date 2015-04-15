class Bob
  def hey(txt)
    return fine if txt.strip.empty?
    return woah if txt == txt.upcase
    return sure if txt =~/\?$/
    whatever
  end

  private

  def fine
    'Fine. Be that way!'
  end

  def woah
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def whatever
    "Whatever."
  end
end
