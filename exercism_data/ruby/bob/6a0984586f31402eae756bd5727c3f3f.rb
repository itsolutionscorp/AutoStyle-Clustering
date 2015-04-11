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
  def initialize
    @possible_comprehensions = {
      ignored:    ->(message) { message.strip.empty? },
      shouted_at: ->(message) { message == message.upcase },
      questioned: ->(message) { message.end_with? '?' },
      default:    ->(message) { true }
    }
  end

  def comprend_as sentence
    @possible_comprehensions.find do |_, detector| 
      detector.call sentence 
    end.first
  end
end

class Bob
  include TeenageResponses

  def initialize(comprehender=MessageComprehension.new, responses=TeenageResponses.new)
    @comprehender = comprehender
    @responses = responses
  end

  def hey sentence
    @responses.to(comprehend(sentence))
  end

  def comprehend sentence
    @comprehender.comprend_as sentence
  end
end
