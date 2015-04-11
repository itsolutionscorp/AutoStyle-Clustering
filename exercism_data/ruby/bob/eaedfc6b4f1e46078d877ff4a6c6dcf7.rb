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
  def initialize replies
    @replies = vocabulary
    @memory = {}
  end

  def listen phrase
    @phrase = phrase
  end

  def think
    @memory[@phrase] = capture_meaning if @memory[@phrase].nil?
  end

  def reply
    @replies[@memory[@phrase]]
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
    @phrase.nil? || @phrase.strip.empty?
  end

  def shout?
    @phrase.upcase == @phrase
  end

  def question?
    @phrase.end_with? "?" 
  end
end
