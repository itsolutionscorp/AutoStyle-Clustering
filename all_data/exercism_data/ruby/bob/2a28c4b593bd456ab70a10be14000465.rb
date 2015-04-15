class Bob
  def hey(message)
    prepared_message=prepare(message)

    case
    when prepared_message.empty? then "Fine. Be that way!"
    when yell?(prepared_message) then "Woah, chill out!"
    when question?(prepared_message) then "Sure."
    else "Whatever."
    end
  end

  private
  def prepare(message)
    message.strip
  end

  def yell?(message)
    message.upcase == message && message=~/[A-Za-z]/
  end

  def question?(message)
    message.end_with?("?")
  end

end
