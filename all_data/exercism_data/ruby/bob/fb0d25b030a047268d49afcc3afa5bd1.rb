class Bob
  def hey(input)
    remark = Remark.new input.to_s
    if remark.nothing?
      'Fine. Be that way.'
    elsif remark.yelling?
      'Woah, chill out!'
    elsif remark.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  class Remark < String
    def nothing?
      self.empty?
    end

    def yelling?
      self.upcase == self
    end

    def question?
      self.end_with? '?'
    end
  end
end
