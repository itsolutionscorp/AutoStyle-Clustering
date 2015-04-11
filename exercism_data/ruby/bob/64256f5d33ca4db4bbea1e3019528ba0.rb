class Bob
  attr_reader :message

  def initialize(message = nil)
    @message = message
  end

  def hey(message)
    @message ||= message

    if blank?
      "Fine. Be that way!"
    elsif yelling?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  def blank?
    # message contains no non-whitespace
    message !~ /\S/
  end

  def yelling?
    # message contains alpha characters and is all uppercase
    message =~ /[a-z]/i && message.upcase == message
  end

  def question?
    # message ends with a question mark
    message[-1, 1] == "?"
  end
end
