class Bob
  def hey(message)
    respond_to(message)
  end

  private
  def respond_to(message)
    phrases.detect { |phrase| phrase.can_respond?(message) }.response
  end

  def phrases
    [Fine.new, ChillOut.new, Sure.new, Whatever.new]
  end
end

module PhraseResponder
  attr_reader :condition, :response

  def can_respond?(message)
    condition.call(message)
  end

  def say(phrase)
    @response = phrase
    self
  end

  def when(&block)
    @condition = block
    self
  end

  def anytime
    @condition = -> (_) { true }
    self
  end
end

class Fine
  include PhraseResponder

  def initialize
    say('Fine. Be that way.')
    .when { |message|
      message.nil? or message.empty?
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
    say('Whatever.').anytime
  end
end
