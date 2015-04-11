class Bob
  def hey(message)
    analyzer = StatementAnalyzer.new(message)
    return "Fine. Be that way!" if analyzer.ignoring?
    return "Woah, chill out!" if analyzer.shouting?
    return "Sure." if analyzer.questioning?
    "Whatever."
  end
end

class StatementAnalyzer
  attr_reader :message

  def initialize(message)
    @message = message
  end

  def questioning?
    message.end_with?("?")
  end

  def shouting?
    message == message.upcase
  end

  def ignoring?
    message.strip.empty?
  end
end
