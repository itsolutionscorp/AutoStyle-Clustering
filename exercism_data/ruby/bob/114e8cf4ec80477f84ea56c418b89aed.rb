class Bob

  def hey(message)

    tell_bob message

    with_each_tone do |tone|
      return response_for tone if is_talking_in? tone
    end
  end


  def tell_bob message
    @message = message
  end

  def is_talking_in? tone
    self.send(tone)
  end

  def with_each_tone
    tones.each do |tone|
      yield tone
    end
  end

  def tones
    [:silence,:yelling,:asking,:always]
  end

  def response_for tone
    response[tone]
  end

  def response
    {
      silence: 'Fine. Be that way!',
      yelling: 'Woah, chill out!',
      asking: 'Sure.',
      always: 'Whatever.'
    }
  end

  def silence
    @message.to_s.strip.empty?
  end

  def yelling
    @message.upcase == @message
  end

  def asking
    @message.end_with? '?'
  end

  def always
    true
  end
end
