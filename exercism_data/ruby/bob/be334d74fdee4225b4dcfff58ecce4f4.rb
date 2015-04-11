class Bob

  def hey msg
    return "Fine. Be that way." if !msg || msg.empty?

    if msg == msg.upcase
      "Woah, chill out!"
    else
      last_char = msg[-1]
      if last_char == '?'
        "Sure."
      else
        "Whatever."
      end
    end
  end

end
