class Bob
  def initialize
  end

  def hey(remark)
    return 'Whoa, chill out!' if is_yell?(remark)
    return 'Sure.' if is_question?(remark)
    return 'Fine. Be that way!' if is_meanless?(remark)
    return 'Whatever.'
  end

  private

  def is_question?(remark)
    (clean(remark) =~ /.*\?$/) == 0
  end

  def is_yell?(remark)
    return (clean(remark).gsub(/\d+/,'') =~ /^[A-Z]+\??$/) == 0 
  end

  def is_meanless?(remark)
    (remark =~ /[a-zA-Z0-9]/).nil?
  end

  def clean(remark)
    remark.gsub(/[^0-9A-Za-z?]/, '')
  end

end
