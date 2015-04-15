module TeenageResponder
  RESPONSES = Hash.new('Whatever.')
  RESPONSES[:silence] = 'Fine. Be that way!'
  RESPONSES[:shouting] = 'Woah, chill out!'
  RESPONSES[:question] = 'Sure.'

  def respond_to(perceived_emotion)
    RESPONSES[perceived_emotion]
  end
end

module TeenageInterpreter
  def interpret(phrase)
    phrase = Phrase.new(phrase)
    return :silence if phrase.silence?
    return :shouting if phrase.shouting?
    return :question if phrase.question?
    nil
  end
end

class TeenageBrain
  include TeenageResponder
  include TeenageInterpreter

  def interpret_and_respond_to(phrase)
    respond_to(interpret(phrase))
  end
end

class Bob
  def hey(phrase)
    brain.interpret_and_respond_to(phrase)
  end

  private
  def brain
    TeenageBrain.new
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
