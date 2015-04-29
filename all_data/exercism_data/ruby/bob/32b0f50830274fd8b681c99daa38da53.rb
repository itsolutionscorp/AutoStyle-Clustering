class Bob

  def hey text

    sentence = Sentence.new(text.to_s)

    if sentence.silent?
      'Fine. Be that way.'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.asking?
      'Sure.'
    else
      'Whatever.'
    end

  end

end


class Sentence < String

  def silent?
    self.empty?
  end

  def yelling?
    self.upcase == self
  end

  def asking?
    self.end_with?('?')
  end

end
