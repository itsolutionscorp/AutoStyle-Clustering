class Bob
  def hey(message_string)
    message = Message.new(message_string)
    case when message.is_all_caps?
      "Woah, chill out!"
    when message.is_a_question?
      "Sure."
    when message.is_empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end

class Message
  def initialize(message_string)
    @message_string = message_string.strip
  end

  def is_all_caps?
    contains_letters? && contains_no_lowercase_letters?
  end

  def is_a_question?
    @message_string[-1] == "?"
  end

  def is_empty?
    @message_string.empty?
  end

  private
  def contains_letters?
    !(/[a-zA-Z]/ =~ @message_string).nil?
  end

  def contains_no_lowercase_letters?
    (/[a-z]/ =~ @message_string).nil?
  end
end
