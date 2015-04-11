class Bob
  RESPONSES = { 
    question: 'Sure.',
    yelling: 'Woah, chill out!',
    silence: 'Fine. Be that way!'
  }

  def hey(speech)
    @speech = Speech.new(speech)
    respond_to @speech
  end

  private

  def respond_to(speech)
    response_for speech.type
  end

  def response_for(type)
    RESPONSES[type] || 'Whatever.'
  end

end

class Speech

  def initialize(speech)
    @speech = speech
  end

  def type
    return :silence if is_silence?
    return :yelling if is_yelling?
    return :question if is_question?
    return :other
  end

  private

  def is_question?
   !!( /\?\z/ =~ @speech)
  end

  def is_yelling?
    @speech === @speech.upcase
  end

  def is_silence?
    @speech.empty? || @speech.strip.empty?
  end

end
