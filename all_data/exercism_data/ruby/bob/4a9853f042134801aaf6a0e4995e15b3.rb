class Bob

  def hey string

    sentence = Sentence.new(string)

    if sentence.empty?
      'Fine. Be that way.'
    elsif sentence.yelling?
      'Woah, chill out!'
    elsif sentence.question?
      'Sure.'
    else
      'Whatever.'
    end

  end

end


class Sentence < String

  def initialize string
    if string.nil?
      super(string.to_s)
    else
      super
    end
  end

  def yelling?
    self.upcase == self
  end

  def question?
    self.end_with?('?')
  end

end
