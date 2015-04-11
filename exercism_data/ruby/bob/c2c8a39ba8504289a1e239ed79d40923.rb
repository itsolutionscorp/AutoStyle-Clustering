class Bob

  def hey( phrase )

    phrase = AdultGibberish.new( phrase )

    case 
    when phrase.is_silent?
      'Fine. Be that way!'
    when phrase.is_shouting?
      'Woah, chill out!'
    when phrase.is_question?
      'Sure.'
    else
      'Whatever.'
    end

  end

end

class AdultGibberish < String 

  attr_reader :phrase

  def initialize( phrase )
    @phrase = phrase
  end

  def is_question?
    phrase.end_with? '?'
  end

  def is_silent?
    phrase.strip.empty?
  end

  def is_shouting?
    phrase.upcase == phrase 
  end

end
