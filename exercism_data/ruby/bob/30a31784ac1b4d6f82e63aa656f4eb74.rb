class Bob
  def hey(msg)
    case
    when msg.nil?, msg.length == 0 then "Fine. Be that way."
    when msg.upcase == msg then "Woah, chill out!"
    when msg[-1] == '?' then "Sure."
    else
      "Whatever."
    end
  end
end
