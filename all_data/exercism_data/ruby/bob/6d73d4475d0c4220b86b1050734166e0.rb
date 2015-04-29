class Bob

  def hey(remark)
    if silence?(remark)
      "Fine. Be that way."
    elsif yell?(remark) 
      "Woah, chill out!"
    elsif question?(remark)
      "Sure."
    else
      "Whatever."
    end
  end

  private
  def question?(remark)
    remark.end_with? "?"
  end

  def yell?(remark)
    remark.upcase == remark
  end

  def silence?(remark)
    remark.nil? || remark.empty?
  end

end
