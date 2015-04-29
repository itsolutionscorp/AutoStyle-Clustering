class Bob 
  def hey(remark)
    return "Fine. Be that way!" if silence? remark 
    return "Whoa, chill out!" if shouting? remark 
    return "Sure." if question? remark 
    return "Whatever."
  end

  private
  
  def silence? remark
    remark =~ /\A\s*\z/  
  end

  def shouting? remark
    remark =~ /^[[A-Z]+\s]+\??$/ || remark =~ /[^a-z]+!$/
  end

  def question? remark
    remark =~ /[?]\z/
  end

end
