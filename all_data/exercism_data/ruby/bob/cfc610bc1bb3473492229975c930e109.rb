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

  def listen phrase
    @phrase = phrase.squeeze.strip
  end

  def think
    @memory[@phrase] ||= capture_meaning
  end

  def reply
    @vocabulary[@memory[@phrase]]
  end

  private
  def capture_meaning
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

  def silence?
    @phrase.nil? || @phrase.empty?
  end

  def shout?
    @phrase.upcase == @phrase
  end

  def question?
    @phrase.end_with? "?" 
  end
end
