class Bob
  def hey(message)
    message = Message.new(message)

    if message.nothing?
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

class Message
  attr_reader :string

  def initialize(string)
    @string = string.to_s.strip
  end

  def nothing?
    string.empty?
  end

  def yelling?
    string.upcase == string
  end

  def question?
    string.end_with?("?")
  end
end
