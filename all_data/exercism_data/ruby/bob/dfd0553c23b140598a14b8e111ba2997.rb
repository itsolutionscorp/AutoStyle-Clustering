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

  ORDERED_TYPES = [
    :silence,
    :yelling,
    :question
  ]

  def initialize(text)
    @speech = text
  end

  def type
    ORDERED_TYPES.each do |type|
      if self.send("#{type}?")
        return type
      end
    end
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
    @speech.strip.empty?
  end

end
