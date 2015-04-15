class Bob
  def hey remark
    return "Whoa, chill out!" if shouting? remark
    return "Sure." if question? remark 
    return "Fine. Be that way!" if silence? remark 
    "Whatever."
  end

  private
  def shouting? remark
    /[A-Z]/.match(remark) && remark == remark.upcase
  end

  def question? remark
    remark.split(//).last == "?"
  end

  def silence? remark
    remark.strip.empty?
  end
end
