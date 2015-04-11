class Bob

  def hey(remark)
    if is_silence(remark)
      "Fine. Be that way."
    elsif is_yell(remark) 
      "Woah, chill out!"
    elsif is_question(remark)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def is_question(remark)
    remark.end_with? "?"
  end

  def is_yell(remark)
    remark.upcase == remark
  end

  def is_silence(remark)
    remark.nil? || remark.empty?
  end

end
