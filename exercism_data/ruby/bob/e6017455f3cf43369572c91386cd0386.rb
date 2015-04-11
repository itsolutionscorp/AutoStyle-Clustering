class Bob
  RESPOND_TO_SILENCE = 'Fine. Be that way!'
  RESPOND_TO_SHOUTING = 'Woah, chill out!'
  RESPOND_TO_QUESTION = 'Sure.'
  RESPOND_TO_OTHER = 'Whatever.'

  def hey mesg
    m = Message.new(mesg)
    if m.silence?
      RESPOND_TO_SILENCE
    elsif m.shout?
      RESPOND_TO_SHOUTING  
   elsif m.question?
      RESPOND_TO_QUESTION
    else
      RESPOND_TO_OTHER
    end
  end
end

class Message
  attr_accessor :mesg
  def initialize mesg
    @mesg = mesg
  end

  def shout?
      mesg == mesg.upcase
  end

  def question?
    mesg.end_with?('?')
  end

  def silence?
    mesg.strip.size == 0
  end
end
