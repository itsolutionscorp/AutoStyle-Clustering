class Bob
  def hey(arg)
    response = Message.new(arg)
    case
      when response.is_yelling?
        "Woah, chill out!"
      when response.is_question?
        "Sure."
      when response.silence?
        "Fine. Be that way!"
      else
        "Whatever."
    end
  end
end

class Message
  def initialize(string)
    @string= string
  end

  def is_yelling?
    (@string == @string.upcase) && (@string =~ /[A-Z]/)
  end

  def is_question?
    @string.end_with?("?")
  end

  def silence?
    @string.strip.empty?
  end
end
