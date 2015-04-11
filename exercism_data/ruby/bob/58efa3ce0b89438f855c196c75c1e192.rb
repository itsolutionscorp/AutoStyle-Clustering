class DefaultResponse
  attr_reader :msg

  def initialize(msg)
    @msg = msg
  end

  def respond
    matcher[msg] && response
  end

  def matcher
    ->(msg){ true }
  end

  def response
    'Whatever.'
  end
end

class SurlyResponse < DefaultResponse
  def matcher
    ->(msg){ !msg.match(/[a-z]/) }
  end

  def response
    'Woah, chill out!'
  end
end

class UninterestedResponse < DefaultResponse
  def matcher
    ->(msg){ msg.end_with? '?' }
  end

  def response
    'Sure.'
  end
end

class GrumpyResponse < DefaultResponse
  def matcher
    ->(msg){ msg.match(/^\s*$/) }
  end

  def response
    'Fine. Be that way!'
  end
end

class Bob

  def hey(msg)
    possible_responses_to(kill_newlines(msg)).
      map(&:respond).
      reject(&false_response).
      first
  end

  def possible_responses_to msg
    [
      GrumpyResponse,
      SurlyResponse,
      UninterestedResponse,
      DefaultResponse,
    ].map{ |klass| klass.new(msg) }
  end

  def kill_newlines msg
    msg.gsub("\n", '')
  end

  def false_response
    ->(response){ !response }
  end
end
