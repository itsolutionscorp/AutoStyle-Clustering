class String

  def blank?
    to_s.strip == ''
  end

  def upcase?
    self == self.upcase
  end

  def question?
    end_with? '?'
  end

end

class Communication

  def initialize(string)
    @rant = string.to_s
  end

  def silent_treatment?
    @rant.blank?
  end

  def yelling?
    @rant.upcase?
  end

  def asking_something?
    @rant.question?
  end

end

class Bob

  def hey(rant)

    exchange = Communication.new rant

    if exchange.silent_treatment? then 'Fine. Be that way!'
    elsif exchange.yelling? then 'Woah, chill out!'
    elsif exchange.asking_something? then 'Sure.'
    else
      'Whatever.'
    end

  end

end
