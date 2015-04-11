class Bob

  def initialize
  end

  def hey(words)
    if shouting(words)
      'Woah, chill out!'
    elsif words == ''
      'Fine, be that way.'
    elsif question(words)
      'Sure.'
    elsif statement(words)
      'Whatever.'
    end
  end

private
  def shouting(words)
    words.split.all? {|word| word == word.upcase } && words != ''
  end

  def statement(words)
    words.split.any? {|word| word == word.downcase }
  end

  def question(words)
    words.split.last.split(//).last == "?"
  end
end
