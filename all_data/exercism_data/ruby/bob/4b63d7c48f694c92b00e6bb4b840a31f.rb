class Bob
  def hey(message)
    case
    when blank?(message) then "Fine. Be that way!"
    when yell?(message) then "Woah, chill out!"
    when question?(message) then "Sure."
    else "Whatever."
    end
  end

  private
  def blank?(message)
    message.strip.empty?
  end

  def yell?(message)
    message.upcase == message && message=~/[A-Za-z]/
  end

  def question?(message)
    message.end_with?("?")
  end
end
