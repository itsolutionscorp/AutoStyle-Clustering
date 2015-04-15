class Bob
  def hey(phrase)
    p = Phrase.new
      case
        when p.silence?(phrase)
          'Fine. Be that way!'
        when p.yelling?(phrase)
          'Woah, chill out!'
        when p.questioning?(phrase)
          'Sure.'
        else
          'Whatever.'
        end
      end
  end

class Phrase
  def yelling?(phrase)
    phrase.strip.upcase == phrase
  end

  def questioning?(phrase)
    phrase.strip.end_with?('?')
  end

  def silence?(phrase)
    phrase.strip.empty?
  end
end
