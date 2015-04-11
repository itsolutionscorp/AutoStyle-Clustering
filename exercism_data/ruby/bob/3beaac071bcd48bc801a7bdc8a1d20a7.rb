class Bob
  REPLIES = {
    silent: "Fine. Be that way!",
    angry: "Woah, chill out!",
    inquisitive: "Sure.",
    unknown: "Whatever."
  }

  def hey(message)
    tone = ToneDetector.new(message).detect
    REPLIES[tone]
  end
end

class ToneDetector
  TONES = [:silent, :angry, :inquisitive]

  attr_reader :message

  def initialize(message)
    @message = message
  end

  def detect
    TONES.find { |m| send "#{m}?" } || :unknown
  end

  def silent?
    message.nil? || message.strip == ""
  end

  def angry?
    message == message.upcase
  end

  def inquisitive?
    message.end_with?("?")
  end
end
