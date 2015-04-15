class Bob
  def hey phrase
    phrase = Phrase.new phrase

    case
    when phrase.silence?
      'Fine. Be that way!'
    when phrase.shout?
      'Woah, chill out!'
    when phrase.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Phrase
  def initialize phrase
    @phrase = phrase.to_s
  end

  def silence?
    @phrase.empty?
  end

  def shout?
    @phrase == @phrase.upcase
  end

  def question?
    return false if shout?
    @phrase.end_with?('?')
  end
end
