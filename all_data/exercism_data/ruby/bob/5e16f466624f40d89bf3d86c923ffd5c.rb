class Bob

  def hey(msg)
    msg = msg.to_s
    return 'Fine. Be that way!' if silence? msg

    return 'Woah, chill out!' if shout? msg

    return 'Sure.' if query? msg

    'Whatever.'
  end

  def shout?(msg)
    msg == msg.upcase
  end

  def query?(msg)
    msg.end_with? '?'
  end

  def silence?(msg)
    msg.strip.empty?
  end
end
