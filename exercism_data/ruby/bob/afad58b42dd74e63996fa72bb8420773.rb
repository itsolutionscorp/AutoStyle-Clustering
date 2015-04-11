class Bob
  attr_reader :message

  def initialize(message = nil)
    @message = message
  end

  def hey(message = nil)
    @message ||= message

    if blank?
      "Fine. Be that way!"
    elsif all_upcase?
      "Woah, chill out!"
    elsif is_question?
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def blank?
    message !~ /\S/ || message.empty?
  end

  def all_upcase?
    message =~ /[a-z]/i && message.upcase == message
  end

  def is_question?
    message !~ /\n/ && message[-1, 1] == "?"
  end
end
