class Bob

  def hey(reply)
    case
    when reply.nil? || reply.length == 0
      "Fine. Be that way."
    when !reply.match(/[a-z]/)
      "Woah, chill out!"
    when reply[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end

end
