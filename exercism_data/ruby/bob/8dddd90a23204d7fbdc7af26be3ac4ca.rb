class Bob
  RESPOND_TO_SILENCE = 'Fine. Be that way!'
  RESPOND_TO_SHOUTING = 'Woah, chill out!'
  RESPOND_TO_QUESTION = 'Sure.'
  RESPOND_TO_OTHER = 'Whatever.'

  def hey mesg
    user_mesg = Message.new(mesg)
    if user_mesg.silence?
      RESPOND_TO_SILENCE
    elsif user_mesg.shout?
      RESPOND_TO_SHOUTING  
   elsif user_mesg.question?
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
