class Bob
  def hey(message)
    response_for(message)
  end

  private
  def response_for(message)
    phrases.detect { |r| r.can_respond?(message) }.response
  end

  def phrases
    [Fine.new, ChillOut.new, Sure.new, Whatever.new]
  end
end

module PhraseResponder
  attr_reader :can_respond, :phrase

  def can_respond?(message)
    can_respond.call(message)
  end

  def response
    phrase
  end
end

class Fine
  include PhraseResponder
  def initialize
    @phrase = 'Fine. Be that way.'
    @can_respond = -> (message) {
      not message or message.empty?
    }
  end
end

class ChillOut
  include PhraseResponder
  def initialize
    @phrase = 'Woah, chill out!'
    @can_respond = -> (message) {
      message.upcase == message
    }
  end
end

class Sure
  include PhraseResponder
  def initialize
    @phrase = 'Sure.'
    @can_respond = -> (message) {
      message.end_with?('?')
    }
  end
end

class Whatever
  include PhraseResponder
  def initialize
    @phrase = 'Whatever.'
    @can_respond = -> (_) { true }
  end
end
