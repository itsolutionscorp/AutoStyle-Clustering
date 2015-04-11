class Bob
  attr_reader :message

  def initialize(message = nil)
    @message = message
  end

  def hey(message = "")
    @message ||= message

    if nothing?
      "Fine. Be that way!"
    elsif yelling?
      "Woah, chill out!"
    elsif question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def nothing?
    message !~ /\S/ || message.empty?
  end

  def yelling?
    message =~ /[a-z]/i && message.upcase == message
  end

  def question?
    message !~ /\n/ && message[-1, 1] == "?"
  end
end
