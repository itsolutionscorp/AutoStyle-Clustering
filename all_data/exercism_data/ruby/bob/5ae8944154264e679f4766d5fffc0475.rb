class Bob
  attr_reader :remark

  def hey(remark)
    @remark = remark
    return 'Whoa, chill out!' if shouting?
    return 'Sure.' if question?
    return 'Fine. Be that way!' if no_content?
    'Whatever.'
  end

  def shouting?
    remark.match(/[A-Z]/) && remark.upcase == remark
  end

  def question?
    remark[-1] == '?'
  end

  def no_content?
    remark.match(/\A\s*\Z/)
  end
end
