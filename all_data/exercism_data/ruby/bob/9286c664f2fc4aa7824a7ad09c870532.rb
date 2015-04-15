require 'delegate'

class Bob
  TEENAGER_TALK = {
    :silence => "Fine. Be that way!",
    :shout => "Woah, chill out!",
    :question => "Sure.",
    :anything_else => "Whatever."
  } 

  def initialize
    @brain = Brain.new(TEENAGER_TALK)
  end
 
  def hey phrase
    @brain.listen phrase
    @brain.think
    @brain.reply
  end
end

class Brain
  def initialize vocabulary
    @vocabulary = vocabulary
    @memory = {}
  end

  def listen sound 
    @phrase = Phrase.new(sound)
  end

  def think
    @memory[@phrase.to_s] ||= @phrase.meaning
  end

  def reply
    @vocabulary[@memory[@phrase.to_s]]
  end
end

class Phrase < SimpleDelegator
  def initialize message
    super(message.to_s.strip.squeeze)
  end

  def meaning
    case
    when silence?
      :silence
    when shout?
      :shout
    when question?
      :question
    else
      :anything_else
    end 
  end

  private
  def silence?
    empty?
  end

  def shout?
    upcase == self 
  end

  def question?
    end_with? "?" 
  end
end
