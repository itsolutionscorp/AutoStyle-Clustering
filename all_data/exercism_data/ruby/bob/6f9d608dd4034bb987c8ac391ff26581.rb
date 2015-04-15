class Communication

  def initialize(string)
    @rant = string.to_s
  end

  def silent_treatment?
    @rant.strip.empty?
  end

  def yelling?
    @rant == @rant.upcase
  end

  def asking_something?
    @rant.end_with? '?'
  end

end

class Bob

  def hey(rant)

    exchange = Communication.new rant

    return 'Fine. Be that way!' if exchange.silent_treatment?
    return 'Woah, chill out!'   if exchange.yelling?
    return 'Sure.'              if exchange.asking_something?

    'Whatever.'

  end

end
