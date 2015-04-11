class Bob
  attr_reader :remark

  def hey(remark)
    @remark = remark
    silence or shouting or question or gibberish or unknown
  end

  private

  def silence
    'Fine. Be that way!' if silence?
  end

  def silence?
    remark.strip.empty?
  end

  def shouting
    'Whoa, chill out!' if shouting?
  end

  def shouting?
    remark.upcase == remark && !only_numbers?
  end

  def only_numbers?
    remark[/[^a-zA-Z]+/] == remark
  end

  def question
    'Sure.' if question?
  end

  def question?
    remark.end_with?('?')
  end

  def gibberish
    'Whoa, chill out!' if gibberish?
  end

  def gibberish?
    !remark.include?(' ')
  end

  def unknown
    'Whatever.'
  end
end
