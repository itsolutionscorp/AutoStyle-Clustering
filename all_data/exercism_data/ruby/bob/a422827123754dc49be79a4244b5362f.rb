class Bob

  def hey(message)
    message = Ballyhoo.new(message)
    case
    when message.shouting?
      'Woah, chill out!'
    when message.nosey?
      'Sure.'
    when message.giving_silent_treatment?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end

class Ballyhoo
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def shouting?
    message =~ /[A-Z+]/ && message == message.upcase
  end

  def nosey?
    message.end_with?("?")
  end

  def giving_silent_treatment?
    message.strip.empty?
  end
end
