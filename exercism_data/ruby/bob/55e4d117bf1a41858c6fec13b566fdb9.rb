module Reply
  class SilenceReplier
    def reply_to(message)
      'Fine. Be that way!' if message.silent?
    end
  end

  class ShoutingReplier
    def reply_to(message)
      'Whoa, chill out!' if message.shouting?
    end
  end

  class QuestionReplier
    def reply_to(message)
      'Sure.' if message.question?
    end
  end

  class DefaultReplier
    def reply_to(message)
      'Whatever.'
    end
  end

  class ReplyManager
    def initialize(*repliers)
      @repliers = repliers
      @repliers << DefaultReplier.new
    end

    def reply_to(message)
      @repliers.each do |replier|
        reply = replier.reply_to(message)

        return reply if reply
      end
    end
  end
end

class Message
  attr_accessor :content

  def initialize(content)
    @content = content
  end

  def silent?
    @content.strip == ''
  end

  def shouting?
    @content == @content.upcase && @content != @content.downcase
  end

  def question?
    @content.end_with? '?'
  end
end

class Bob 
  def initialize
    @reply_manager = Reply::ReplyManager.new(Reply::SilenceReplier.new, Reply::ShoutingReplier.new, Reply::QuestionReplier.new)
  end

  def hey(input)  
    message = Message.new(input)
    
    @reply_manager.reply_to(message)
  end
end
