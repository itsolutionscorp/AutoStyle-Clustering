class Bob
  def hey(remark)
    yell?(remark) || question?(remark) || silence?(remark) || 'Whatever.'
  end

  private

  def question?(remark)
    'Sure.' if remark.end_with? '?'
  end

  def yell?(remark)
    'Whoa, chill out!' if remark =~ /[A-Z]/ && remark == remark.upcase
  end

  def silence?(remark)
    'Fine. Be that way!' if remark.strip == ''
  end
end
