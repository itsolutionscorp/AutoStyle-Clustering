class Bob
  def hey(remark)
    yell?(remark) || question?(remark) || silence?(remark) || 'Whatever.'
  end

  private

  def question?(remark)
    'Sure.' if remark.chars.last == '?'
  end

  def yell?(remark)
    'Whoa, chill out!' if remark =~ /[A-Z]/ && remark == remark.upcase
  end

  def silence?(remark)
    'Fine. Be that way!' if remark.gsub(/\s/,'') == ''
  end
end
