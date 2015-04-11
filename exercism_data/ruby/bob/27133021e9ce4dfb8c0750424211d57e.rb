class Bob
  def bob
  end

  def hey(remark)
    case
    when yelling?(remark)
      'Whoa, chill out!'
    when question?(remark)
      'Sure.'
    when silent?(remark)
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  private

  def yelling?(remark)
    remark == remark.upcase && remark.match(/[a-zA-Z]/)
  end

  def question?(remark)
    remark.chars.last == '?'
  end

  def silent?(remark)
    remark.empty? || remark.squeeze == ' ' || remark.squeeze == "\t"
  end
end
