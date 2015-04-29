class Bob

  def hey(remark)
    return 'Whoa, chill out!' if is_yelling? remark
    return 'Sure.' if is_question? remark
    return 'Fine. Be that way!' if is_silence? remark
    return 'Whatever.'
  end

  def is_yelling?(remark)
    remark == remark.upcase && remark != remark.downcase
  end

  def is_question?(remark)
    remark.end_with?('?')
  end

  def is_silence?(remark)
    remark.strip.empty?
  end

end
