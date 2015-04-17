class Bob
  def hey(message)
    case Message.new(message)
    when :question?  then answer "Sure."
    when :statement? then answer "Whatever."
    when :shouting?  then answer "Woah, chill out!"
    when :silence?   then answer "Fine. Be that way."
    end
  end

  private

  def answer(message)
    message
  end
end

Message = Struct.new(:value) do
  def initialize(value)
    super(String(value))
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
    value =~ /^[^a-z]+$/
  end

  def ends_with?(string)
    value.end_with?(string)
  end

  def blank?
    value == ""
  end
end

class Symbol
  def ===(other)
    if other.is_a?(Message)
      other.send(self)
    else
      super
    end
  end
end