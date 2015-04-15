class Bob
  def hey(message)
    message ||= ""
    message = Message.new(message)
 
  case
    when message.shouting?
      "Woah, chill out!"
    when message.question?
      "Sure."
    when message.empty?
      "Fine. Be that way."
    else
      "Whatever."
    end
  end
 
  private
  class Message < String
    def shouting?
      return false if empty?
      self == upcase
    end
 
    def question?
      end_with? "?"
    end
  end
end
