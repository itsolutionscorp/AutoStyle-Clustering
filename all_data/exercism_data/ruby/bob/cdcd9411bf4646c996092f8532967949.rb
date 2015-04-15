class Bob
  def hey(phrase)
    phrase = sanitize(phrase)
    case 
    when shouting?(phrase)
      'Woah, chill out!'
    when asking?(phrase)
      'Sure.'
    when phrase.length == 0
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def asking?(phrase)
    phrase.match(/\?$/)
  end

  def sanitize(phrase)
    phrase.strip.gsub(/\n/, '')
  end

  def shouting?(phrase)
    phrase.upcase == phrase && phrase.upcase.match(/[A-Z]/)
  end
end
