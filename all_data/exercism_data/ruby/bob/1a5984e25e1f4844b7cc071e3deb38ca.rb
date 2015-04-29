class Bob
  def hey remark
    if shouting? remark
      'Whoa, chill out!'
    elsif asking_a_question? remark
      'Sure.'
    elsif silence? remark
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def shouting? remark
    !remark.scan(/[a-z]/).any? && remark.scan(/[A-Z]/).any?
  end

  def silence? remark
    !remark.scan(/[\w\d]/).any?
  end

  def asking_a_question? remark
    remark.chars.last == "?"
  end
end
