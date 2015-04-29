module TeenageResponses
  def response_to message_type
    {
      ignored:    'Fine. Be that way!',
      shouted_at: 'Woah, chill out!',
      questioned: 'Sure.',
      default:    'Whatever.'
    }[message_type]
  end
end


class MessageComprenension
  def initialize
    @possible_comprenhensions = {
      ignored:    ->(message) { message.strip.empty? },
      shouted_at: ->(message) { message == message.upcase },
      questioned: ->(message) { message.end_with? '?' },
      default:    ->(message) { true }
    }
  end

  def comprend_as sentence
    @possible_comprenhensions.find do |_, detector| 
      detector.call sentence 
    end.first
  end
end


class Bob
  include TeenageResponses

  def initialize(comprehender=MessageComprenension.new)
    @comprehender = comprehender
  end

  def hey sentence
    response_to(comprehend(sentence))
  end

  def comprehend sentence
    @comprehender.comprend_as sentence
  end
end
