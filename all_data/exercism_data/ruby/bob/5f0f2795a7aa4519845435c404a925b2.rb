class Bob
  class Message < String
    def quiet?;    strip == "";    end
    def shouting?; self == upcase; end
    def asking?;   self =~ /\?$/;  end
  end
  def hey raw_message
    message = Message.new raw_message
    case
    when message.quiet?
      "Fine. Be that way!"
    when message.shouting?
      "Woah, chill out!"
    when message.asking?
      "Sure."
    else
      "Whatever."
    end
  end
end
