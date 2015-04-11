class Bob
  def hey(remark)
    response = ""
    if (remark.upcase == remark) & !(remark.match(/[A-Z]/).nil?) & (remark != "I?")
      return "Whoa, chill out!"
    elsif remark.split(//).last == "?"
      return "Sure."
    elsif remark.sub(/\s+/,"") == ""
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
  end
end
