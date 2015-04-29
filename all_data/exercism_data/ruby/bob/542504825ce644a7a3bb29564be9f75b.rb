class Bob
  DEFAULT_ALPHABET = -> { [Silence.new, Shout.new ,Question.new, Any.new] }
  attr_reader :alphabet
  def initialize(alphabet = nil)
    @alphabet = alphabet || DEFAULT_ALPHABET.call
  end

  def hey(message)
    letter = alphabet.detect { |r| r.can_respond?(message) }
    letter.response
  end
end

class Silence
  def can_respond?(message)
    not message or message.empty?
  end

  def response
    'Fine. Be that way.'
  end
end

class Shout
  def can_respond?(message)
    message.upcase == message
  end

  def response
    'Woah, chill out!'
  end
end

class Question
  def can_respond?(message)
    message.end_with?('?')
  end

  def response
    'Sure.'
  end
end

class Any
  def can_respond?(_)
    true
  end

  def response
    'Whatever.'
  end
end
