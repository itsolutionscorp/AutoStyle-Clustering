class Bob
  def hey(remark)
    if    silence?(remark)  then "Fine. Be that way!"
    elsif yelling?(remark)  then "Woah, chill out!"
    elsif question?(remark) then "Sure."
    else                         "Whatever."
    end
  end

  private
  def silence?(remark)
    remark.strip.empty?
  end

  def yelling?(remark)
    remark == remark.upcase
  end

  def question?(remark)
    remark.end_with?('?')
  end
end
