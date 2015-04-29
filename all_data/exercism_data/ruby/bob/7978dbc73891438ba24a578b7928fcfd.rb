class Bob
  def hey(phrase)
    message = Phrase.new(phrase)
    case
      when message.silence? then 'Fine. Be that way!'
      when message.shouting? then 'Woah, chill out!'
      when message.question? then 'Sure.'
      else 'Whatever.'
    end
  end
end

class Phrase
  attr_reader :phrase

  def initialize(phrase)
    @phrase = phrase
  end

  def question?
    phrase.end_with?('?')
  end

  def shouting?
    phrase.upcase == phrase
  end

  def silence?
    phrase.to_s.strip.empty?
  end
end
