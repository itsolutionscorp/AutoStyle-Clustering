class Bob
  def silence?(remark)
    remark.match(/\A\s*\z/)
  end

  def shout?(remark)
    remark.scan(/[a-z]/).empty? && !remark.scan(/[A-Z]/).empty?
  end

  def question?(remark)
    remark.end_with?('?')
  end

  def hey(remark)
    case
    when silence?(remark)
      'Fine. Be that way!'
    when shout?(remark)
      'Whoa, chill out!'
    when question?(remark)
      'Sure.'
    else
      'Whatever.'
    end
  end
end
