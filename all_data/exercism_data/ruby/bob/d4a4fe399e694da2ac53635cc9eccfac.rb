class Bob

  def hey( phrase )

    phrase = AdultGibberish.new( phrase )

    case 
    when phrase.silent?
      'Fine. Be that way!'
    when phrase.shouting?
      'Woah, chill out!'
    when phrase.question?
      'Sure.'
    else
      'Whatever.'
    end

  end

end

class AdultGibberish

  attr_reader :phrase

  def initialize( phrase )
    @phrase = phrase
  end

  def question?
    phrase.end_with? '?'
  end

  def silent?
    phrase.strip.empty?
  end

  def shouting?
    phrase.upcase!.nil?
  end

end
