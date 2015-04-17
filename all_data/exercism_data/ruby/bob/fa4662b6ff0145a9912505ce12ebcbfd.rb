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
    @phrase = phrase
  end

  def think
    @memory[@phrase] = capture_meaning @phrase if @memory[@phrase].nil?
  end

  def reply
    @vocabulary[@memory[@phrase]]
  end

  def capture_meaning phrase
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
    @phrase.nil? || @phrase.strip.empty?
  end

  def shout?
    @phrase.upcase == @phrase
  end

  def question?
    @phrase.end_with? "?" 
  end
end