class Bob

  def hey string

    sentence = Sentence.new(string)

    if sentence.is_silent?
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

  def initialize string
    super(string.to_s)
  end

  def is_silent?
    self.empty?
  end

  def yelling?
    self.upcase == self
  end

  def asking?
    self.end_with?('?')
  end

end
