class Bob

  def hey(msg)
    return 'Fine. Be that way!' if silence? msg

    return 'Woah, chill out!' if shout? msg

    return 'Sure.' if query? msg

    'Whatever.'
  end

  def shout?(msg)
    msg == msg.upcase
  end

  def query?(msg)
    msg[-1] == '?'
  end

  def silence?(msg)
    msg.to_s.strip == ''
  end
end
