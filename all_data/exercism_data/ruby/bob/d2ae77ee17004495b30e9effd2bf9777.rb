class Bob
  def hey(msg)
    if !msg || msg.gsub(" ","") == ""
      "Fine. Be that way!"
    elsif msg.upcase == msg
      "Woah, chill out!"
    elsif msg.split('').last == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
