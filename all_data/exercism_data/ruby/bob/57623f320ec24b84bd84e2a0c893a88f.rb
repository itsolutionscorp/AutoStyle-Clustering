class Bob
  def hey(remark)
    remark.strip!
    if is_shouting?(remark)
      'Whoa, chill out!'
    elsif is_question?(remark)
      'Sure.'
    elsif is_silence?(remark)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

private
  def is_shouting?(remark)
    remark == remark.upcase and remark != remark.downcase
  end

  def is_question?(remark)
    remark.end_with? '?'
  end

  def is_silence?(remark)
    remark.empty?
  end
end
