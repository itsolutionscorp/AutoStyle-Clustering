class Bob
  def hey(remark)
    if remark.strip == ""
      "Fine. Be that way!"
    elsif remark.upcase == remark && remark.match(/[a-zA-Z]/)
      "Whoa, chill out!"
    elsif remark[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
