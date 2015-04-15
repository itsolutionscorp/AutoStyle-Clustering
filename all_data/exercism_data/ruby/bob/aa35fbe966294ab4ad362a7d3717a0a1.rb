module TeenagerReply
  def reply_to(message)
    handlers = all_handlers
    handlers << DefaultReplyHandler.new

    handlers.inject(false) do
      |result, handler| result ||= handler.reply_to(message)
    end
  end
  
  def all_handlers
    [SilenceReplyHandler.new, ShoutingReplyHandler.new, QuestionReplyHandler.new]
  end

  class SilenceReplyHandler
    def reply_to(message)
      'Fine. Be that way!' if message.silent?
    end
  end

  class ShoutingReplyHandler
    def reply_to(message)
      'Woah, chill out!' if message.shouting?
    end
  end

  class QuestionReplyHandler
    def reply_to(message)
      'Sure.' if message.question?
    end
  end

  class DefaultReplyHandler
    def reply_to(message)
      'Whatever.'
    end
  end
end

class Bob 
  include TeenagerReply

  def hey(input)  
    message = Message.new(input)
    
    reply_to(message)
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
    @content == @content.upcase
  end

  def question?
    @content.end_with? '?'
  end
end
