class Bob

  def hey(msg)
    if msg.gsub(/\s/, "") == ""
      "Fine. Be that way!"
    elsif msg.upcase == msg
      "Woah, chill out!"
    elsif msg.match(/.*\?\z/)
      "Sure."
    else
      "Whatever."
    end
  end

end
