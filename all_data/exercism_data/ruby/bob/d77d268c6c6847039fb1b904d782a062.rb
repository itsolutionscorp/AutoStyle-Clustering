class Bob
  def hey(msg)
    case
    when silence?(msg)  then "Fine. Be that way."
    when loud?(msg)     then "Woah, chill out!"
    when question?(msg) then "Sure."
    else "Whatever."
    end
  end

  def silence?(msg)
    msg.nil? || msg.empty?
  end

  def loud?(msg)
    msg.upcase == msg
  end

  def question?(msg)
    msg.end_with?("?")
  end
end
