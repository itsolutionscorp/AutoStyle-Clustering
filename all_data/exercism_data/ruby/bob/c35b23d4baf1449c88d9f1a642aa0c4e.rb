class Bob
  def hey(msg)
    case
    when msg.nil?, msg.empty? then "Fine. Be that way."
    when msg.upcase == msg    then "Woah, chill out!"
    when msg.end_with?("?")   then "Sure."
    else "Whatever."
    end
  end
end
