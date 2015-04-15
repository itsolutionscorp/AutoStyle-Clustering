class Message
  def initialize(words)
    @words = words
  end

  attr_reader :words

  def yelling?
    words.upcase == words
  end

  def question?
    words.end_with?("?")
  end

  def none?
    # where is active support when you need it! blank?
    words.nil? || words == ""
  end
end

class Bob
  def hey(words)
    message = Message.new(words)

    if message.none?
      "Fine. Be that way!"
    elsif message.yelling?
      "Woah, chill out!"
    elsif message.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
