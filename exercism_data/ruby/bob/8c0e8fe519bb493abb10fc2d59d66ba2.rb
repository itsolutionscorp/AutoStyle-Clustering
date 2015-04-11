class Bob

  def hey(remark)
    remark = remark.to_s.strip
    return "Woah, chill out!"   if ( !remark.empty? && (remark <=> remark.upcase) == 0)
    return "Whatever."          if remark.end_with?('.')
    return "Sure."              if remark.end_with?('?')
    return "Whatever."          if remark.end_with?('!')
    return "Fine. Be that way!" if remark == ''    
  end
end
