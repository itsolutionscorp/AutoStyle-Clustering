class Bob
  def hey(remark)
    return "Fine. Be that way!" if silent?(remark)
    return "Whoa, chill out!" if yelling?(remark)
    return "Sure." if question?(remark)
    "Whatever."
  end

  private
  def yelling?(remark)
    remark == remark.upcase && remark.match(/[[:alpha:]]/)
  end

  def question?(remark)
    remark.end_with?('?')
  end
  
  def silent?(remark)
    remark.strip.empty?
  end
end
