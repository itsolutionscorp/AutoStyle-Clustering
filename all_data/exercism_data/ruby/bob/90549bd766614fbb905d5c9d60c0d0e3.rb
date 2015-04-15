class Bob
  def hey(str)
    message = Message.new(str)

    case
    when message.silent?
      return 'Fine. Be that way!'
    when message.shouting?
      return 'Woah, chill out!'
    when message.question?
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end

class Message
  def initialize(str)
    @str = str
  end

  def shouting?
    if ((@str =~ /\d/) == nil) || ((@str =~ /[A-Z]/) != nil)
      return (@str == @str.upcase)
    end
  end

  def question?
    return (@str[-1] == '?')
  end

  def silent?
    return (@str.strip == '')
  end
end
