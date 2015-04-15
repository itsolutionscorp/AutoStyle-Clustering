class Bob
  attr_reader :message

  def hey(sentence)
    respond_to(Sentence.new(sentence))
  end
  
  private

  def respond_to(sentence)
    Response.respond_to(sentence.type)
  end
end

class Sentence
  TYPES = [:mute, :shout, :question, :whatever]

  attr_reader :sentence

  def initialize(sentence)
    @sentence = sentence
  end

  def type
    TYPES.find { |t| public_send(:"#{t}?") }
  end
  
  def mute?
    sentence.strip == ''
  end

  def shout?
    sentence.upcase == sentence
  end

  def question?
    sentence.end_with?('?')
  end

  def whatever?
    true
  end
end

class Response
  PHRASES = {
    mute:        'Fine. Be that way!',
    shout:       'Woah, chill out!',
    question:    'Sure.',
    whatever:    'Whatever.'
  }

  def self.respond_to(sentence_type)
    PHRASES[sentence_type]
  end
end
