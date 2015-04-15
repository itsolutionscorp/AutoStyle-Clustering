class TeenageResponses
  def to tone
    {
      ignored:    'Fine. Be that way!',
      shouted_at: 'Woah, chill out!',
      questioned: 'Sure.',
      default:    'Whatever.'
    }[tone]
  end
end

class MessageComprehension
  def detect_tone sentence
      return :ignored    if sentence.strip.empty?
      return :shouted_at if sentence == sentence.upcase
      return :questioned if sentence.end_with? '?'
      :default
  end
end

class Bob
  def initialize(comprehender=MessageComprehension.new, responses=TeenageResponses.new)
    @comprehender = comprehender
    @responder = responses
  end

  def hey sentence
    tone = @comprehender.detect_tone(sentence)
    @responder.to tone
  end
end
