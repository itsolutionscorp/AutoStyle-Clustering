require 'active_support/core_ext/object/blank'

class Message
  attr_accessor :message
  
  def initialize message
    @message = message
  end
  
  def says_nothing?
    @message.blank?
  end
  
  def question?
    @message.end_with? '?'
  end
  
  def yelling?
    @message == @message.upcase
  end
end  
  

class Bob
  def hey message
    message = Message.new message
    case
    when message.says_nothing?  then "Fine. Be that way."
    when message.question?      then "Sure."
    when message.yelling?       then "Woah, chill out!"
    else                             "Whatever."  
    end
  end
end