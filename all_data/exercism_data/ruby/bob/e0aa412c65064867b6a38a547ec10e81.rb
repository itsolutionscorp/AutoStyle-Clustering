class Bob
  def hey(remark)
    @remark = remark.to_s.strip

    if silent?
      "Fine. Be that way!"
    elsif shouting?
      "Woah, chill out!"
    elsif telling?
      "Whatever." 
    elsif asking?
      "Sure."
    end
  end

  private

  def silent?
    @remark == ''
  end

  def shouting?
    @remark == @remark.upcase
  end

  def telling?
    @remark.end_with?('.') || @remark.end_with?('!')
  end

  def asking?
    @remark.end_with?('?')
  end

end
