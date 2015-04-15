class Bob

  def hey(remark)
    return "Whoa, chill out!" if yelling?(remark)
    return "Sure." if question?(remark)
    return "Fine. Be that way!" if no_remark?(remark)
    return "Whatever."
  end

  def no_remark?(remark)
    remark.strip.empty?
  end

  def yelling?(remark)
    !remark.scan(/[A-Z]{2,}/).empty? && remark.scan(/[a-z]/).empty?
  end

  def question?(remark)
    !remark.scan(/\?\z/).empty? && !remark.scan(/[a-z0-9]/).empty?
  end

end
