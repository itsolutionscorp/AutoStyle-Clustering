class Bob
  PHRASES = -> { [Fine.new, ChillOut.new, Sure.new, Whatever.new] }
  attr_reader :phrases
  def initialize
    @phrases = PHRASES.call
  end

  def hey(message)
    phrase_for(message)
  end

  def phrase_for(message)
    phrases.detect { |r| r.can_respond?(message) }.response
  end
end

class Phrase

  attr_reader :can_respond, :phrase

  def initialize(phrase,&can_respond)
    @phrase = phrase
    @can_respond = can_respond
  end

  def can_respond?(message)
    can_respond.call(message)
  end

  def response
    phrase
  end
end

class Fine < Phrase
  def initialize
    super('Fine. Be that way.') do |message|
      not message or message.empty?
    end
  end
end

class ChillOut < Phrase
  def initialize
    super('Woah, chill out!') do |message|
      message.upcase == message
    end
  end
end

class Sure < Phrase
  def initialize
    super('Sure.') do |message|
      message.end_with?('?')
    end
  end
end

class Whatever < Phrase
  def initialize
    super('Whatever.') do |_|
      true
    end
  end
end
