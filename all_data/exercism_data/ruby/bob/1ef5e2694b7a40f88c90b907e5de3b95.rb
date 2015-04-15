class Bob
  def hey(message)
    m = Message.new(message)

    case
    when m.question?
      answer "Sure."
    when m.statement?
      answer "Whatever."
    when m.shouting?
      answer "Woah, chill out!"
    when m.silence?
      answer "Fine. Be that way."
    end
  end

  private

  def answer(message)
    message
  end
end

class Message
  def initialize(value)
    @value = String(value)
  end

  def question?
    ends_with? "?" and not all_caps?
  end

  def statement?
    (ends_with? "." or ends_with? "!") and not all_caps?
  end

  def shouting?
    all_caps?
  end

  def silence?
    blank?
  end

  private

  def all_caps?
    @value.upcase == @value and not @value.empty?
  end

  def ends_with?(string)
    @value.end_with?(string)
  end

  def blank?
    @value.empty?
  end
end
