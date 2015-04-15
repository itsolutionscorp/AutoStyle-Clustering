class Bob

    RESPONSES = { 
    question: 'Sure.',
    yelling: 'Woah, chill out!',
    silence: 'Fine. Be that way!'
    }
    RESPONSES.default = 'Whatever.'

  def hey(speech)
    respond_to Speech.new(speech)
  end

  private

  def respond_to(speech)
    response_for speech.type
  end

  def response_for(type)
    RESPONSES[type]
  end

end

class Speech

  def initialize(text)
    @speech = text
  end

  def type
    return :silence if silence?
    return :yelling if yelling?
    return :question if question?
    return :other
  end

  private

  def question?
   !!( /\?\z/ =~ @speech)
  end

  def yelling?
    @speech === @speech.upcase
  end

  def silence?
    @speech.empty? || @speech.strip.empty?
  end

end
