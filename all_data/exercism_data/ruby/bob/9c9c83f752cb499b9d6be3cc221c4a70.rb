class Bob
  def hey(remark)
    case
    when remark.strip.empty?
      "Fine. Be that way!"
    when remark.match(/[A-Z]/) && remark == remark.upcase
      "Whoa, chill out!" #Yelling takes precendence over questions
    when remark[remark.size - 1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
