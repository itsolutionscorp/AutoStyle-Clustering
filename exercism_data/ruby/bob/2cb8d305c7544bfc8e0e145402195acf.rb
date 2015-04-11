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
  attr_reader :respond_condition, :phrase

  def can_respond?(message)
    respond_condition ? respond_condition.call(message) : true
  end

  def response
    phrase
  end

  def say(phrase)
    @phrase = phrase
    self
  end

  def when(&block)
    @respond_condition = block
    self
  end
end

class Fine
  include PhraseResponder

  def initialize
    say('Fine. Be that way.')
    .when { |message|
      not message or message.empty?
    }
  end
end

class ChillOut
  include PhraseResponder

  def initialize
    say('Woah, chill out!')
    .when { |message|
      message.upcase == message
    }
  end
end

class Sure
  include PhraseResponder

  def initialize
    say('Sure.')
    .when { |message|
      message.end_with?('?')
    }
  end
end

class Whatever
  include PhraseResponder

  def initialize
    say('Whatever.')
  end
end
