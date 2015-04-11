class Bob
  def hey(phrase)
    decision = decider.decide_about(phrase)
    voicer.voice_decision(decision)
  end

  private
  def decider
    PhraseDecider.new
  end

  def voicer
    DecisionVoicer.new
  end
end

class DecisionVoicer
  RESPONSES = Hash.new('Whatever.')
  RESPONSES[:silence] = 'Fine. Be that way!'
  RESPONSES[:shouting] = 'Woah, chill out!'
  RESPONSES[:question] = 'Sure.'

  def voice_decision(decision)
    RESPONSES[decision]
  end
end

class PhraseDecider
  def decide_about(phrase)
    phrase = Phrase.new(phrase)
    return :silence if phrase.silence?
    return :shouting if phrase.shouting?
    return :question if phrase.question?
    nil
  end
end

class Phrase
  def initialize(string)
    @string = string
  end

  def silence?
    @string.strip.empty?
  end

  def shouting?
    @string.upcase == @string
  end

  def question?
    @string.end_with? '?'
  end
end
