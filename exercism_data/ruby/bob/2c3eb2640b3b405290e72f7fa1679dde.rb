class Bob
  def hey(message)
    message = Message.new(message)

    if message.nothing?
      utterance(:fine)
    elsif message.yelling?
      utterance(:chill)
    elsif message.question?
      utterance(:sure)
    else
      utterance(:meh)
    end
  end

  private

  UTTERANCES = {
    fine: "Fine. Be that way!",
    chill: "Woah, chill out!",
    sure: "Sure.",
    meh: "Whatever.",
  }

  def utterance(key)
    UTTERANCES[key]
  end
end

class Message
  attr_reader :string

  def initialize(string)
    @string = string.to_s.strip
  end

  def nothing?
    string.empty?
  end

  def yelling?
    !nothing? && string.upcase == string
  end

  def question?
    string.end_with?("?")
  end
end
