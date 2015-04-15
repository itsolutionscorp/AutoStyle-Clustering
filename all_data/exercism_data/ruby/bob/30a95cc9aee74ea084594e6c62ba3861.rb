class Bob
  def hey(remark)
    return 'Whoa, chill out!' if shouting?(remark)
    return 'Sure.' if question?(remark)
    return 'Fine. Be that way!' if no_content?(remark)
    'Whatever.'
  end

  def shouting?(remark)
    remark.match(/[A-Z]/) && remark.upcase == remark
  end

  def question?(remark)
    remark[-1] == '?'
  end

  def no_content?(remark)
    remark.match(/\A\s*\Z/)
  end
end
