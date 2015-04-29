class Bob
  attr_reader :remark

  def hey(remark)
    @remark = remark
    REPLY.fetch(type)
  end

  private

  REPLY = {
    silence: 'Fine. Be that way!',
    shouting: 'Whoa, chill out!',
    question: 'Sure.',
    unknown: 'Whatever.'
  }

  def type
    return :silence if silence?
    return :shouting if shouting?
    return :question if question?
    :unknown
  end

  def silence?
    remark.strip.empty?
  end

  def shouting?
    remark.upcase == remark && remark[/[^a-zA-Z]+/] != remark
  end

  def question?
    remark.end_with?('?')
  end
end
