class Bob

  def hey(remark)
    remark = remark.to_s.strip
    return "Fine. Be that way!" if remark == ''  
    return "Woah, chill out!"   if (remark <=> remark.upcase) == 0
    return "Whatever."          if remark.end_with?('.')
    return "Sure."              if remark.end_with?('?')
    return "Whatever."          if remark.end_with?('!')
  
  end
end
