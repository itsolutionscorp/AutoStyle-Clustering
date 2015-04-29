class Bob
  def hey greeting
    LackadaisicalTeenagerResponder.new(greeting).response
  end
end

class LackadaisicalTeenagerResponder
  def initialize message
    @message = message
  end

  def response
    case
    when silence? then "Fine. Be that way!"
    when yelling? then "Woah, chill out!"
    when question? then "Sure."
    else "Whatever."
    end
  end

  private
  attr_reader :message

  def silence?
    message.strip.empty?
  end

  def yelling?
    message.upcase == message
  end

  def question?
    message.end_with? "?"
  end
end
