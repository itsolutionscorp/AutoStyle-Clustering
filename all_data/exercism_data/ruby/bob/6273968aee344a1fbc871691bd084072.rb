# Bob the lackadaisical teenager.
class Bob
  attr_accessor :message

  def hey(message)
    self.message = message

    if _no_question?
      "Fine. Be that way!"
    elsif _is_yelling?
      "Woah, chill out!"
    elsif _is_a_question?
      "Sure."
    else
      "Whatever."
    end
  end

private

  def _is_a_question?
    message.length > 0 && message.end_with?("?")
  end

  def _is_yelling?
    message.length > 0 && message.upcase == message
  end

  def _no_question?
    message.strip.empty?
  end
end
