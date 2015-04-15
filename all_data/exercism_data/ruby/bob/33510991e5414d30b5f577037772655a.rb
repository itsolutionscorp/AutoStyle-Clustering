class Bob
  def hey(msg)
    case
    when is_nothing?(msg)  then "Fine. Be that way."
    when is_yelling?(msg)  then "Woah, chill out!"
    when is_question?(msg) then "Sure."
    else "Whatever."
    end
  end

  def is_nothing?(msg)
    msg.nil? || msg.empty?
  end

  def is_yelling?(msg)
    msg.upcase == msg
  end

  def is_question?(msg)
    msg.end_with?("?")
  end
end
