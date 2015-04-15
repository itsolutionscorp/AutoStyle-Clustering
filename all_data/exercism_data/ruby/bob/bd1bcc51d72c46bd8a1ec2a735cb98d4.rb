class Bob
  def hey(msg)
    if msg == msg.upcase
      "Woah, chill out!"
    elsif msg.slice(-1,1) == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
