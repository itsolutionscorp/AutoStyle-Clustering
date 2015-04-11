class Bob
  def hey(msg)
    reactions.map{ |reaction| reaction.new(msg) }.detect(&:valid?).answer
  end

  private

  def reactions
    [Shouting, Question, Silent, WhatEver]
  end
end

class Reaction
  def initialize(message)
    @message = message.strip
  end

  def valid?
    raise NotImplementedError, 'Inheriting class must define'
  end

  def answer
    raise NotImplementedError, 'Inheriting class must define'
  end

  private

  def upcase?
    @message == @message.upcase
  end

  def empty?
    @message.empty?
  end
end

class Shouting < Reaction
  def valid?
    upcase? && !empty?
  end

  def answer
    'Woah, chill out!'
  end
end

class Question < Reaction
  def valid?
    @message.end_with?("?")
  end

  def answer
    'Sure.'
  end
end

class Silent < Reaction
  def valid?
    empty?
  end

  def answer
    'Fine. Be that way!'
  end
end

class WhatEver < Reaction
  def valid?
    true
  end

  def answer
    'Whatever.'
  end
end
