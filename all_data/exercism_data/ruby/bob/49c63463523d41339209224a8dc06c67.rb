class Bob
  def hey(text)
    message = ProcessedMessage.new(text)

    if message.all_caps?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    elsif message.blank?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class ProcessedMessage
  attr_reader :message
  def initialize(message)
    @message = message
  end

  def question?
    message[-1,1] == "?"
  end

  def blank?
    message.delete(' ') == ''
  end

  def all_caps?
    (message == message.upcase) && !blank?
  end
end
