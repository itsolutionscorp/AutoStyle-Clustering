class Bob

  def hey phrase
    Phrase.new(phrase).response
  end
end

class Phrase
  attr_reader :phrase

  def initialize phrase
    @phrase = phrase
  end

  def response
    unless invalid?
      case
      when shouting?
        'Woah, chill out!'
      when question?
        'Sure.'
      when nothing?
        'Fine. Be that way!'
      else
        'Whatever.'
      end
    else
      'Whatever.'
    end
  end

  def invalid?
    phrase =~ /^([^ A-Za-z0-9]+)$/
  end

  def shouting?
    phrase == phrase.upcase &&
      phrase =~ /[A-Z]/
  end

  def question?
    phrase[-1] == "?"
  end

  def nothing?
    phrase.strip.length == 0
  end

end
