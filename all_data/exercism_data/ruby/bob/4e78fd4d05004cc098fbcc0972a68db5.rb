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
    @memory[@phrase.sound] ||= @phrase.meaning
  end

  def reply
    @vocabulary[@memory[@phrase.sound]]
  end
end

class Phrase < SimpleDelegator
  def initialize sound 
    super(sound.to_s.strip.squeeze)
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

  def sound
    to_s
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
