class Bob
  def hey(message)
    response_for MessageAnalyzer.new(message)
  end

  def response_for(analyzer)
    case
    when analyzer.silence?
      "Fine. Be that way!"
    when analyzer.shouting?
      "Woah, chill out!"
    when analyzer.questioning?
      "Sure."
    else
      "Whatever."
    end
  end
end

class MessageAnalyzer
  def initialize(message)
    @message = message
  end

  def silence?
    @message.strip.empty?
  end

  def shouting?
    @message.upcase == @message
  end

  def questioning?
    @message.end_with?("?")
  end
end
