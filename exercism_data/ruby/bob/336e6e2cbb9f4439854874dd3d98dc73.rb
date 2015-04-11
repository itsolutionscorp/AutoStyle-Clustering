class Bob
  def hey(msg)
    case
    when nothing?(msg)  then "Fine. Be that way."
    when yelling?(msg)  then "Woah, chill out!"
    when question?(msg) then "Sure."
    else "Whatever."
    end
  end

  def nothing?(msg)
    msg.nil? || msg.empty?
  end

  def yelling?(msg)
    msg.upcase == msg
  end

  def question?(msg)
    msg.end_with?("?")
  end
end
