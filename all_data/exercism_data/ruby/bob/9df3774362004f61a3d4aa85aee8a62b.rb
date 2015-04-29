class Bob
  def hey(msg)
    return 'Woah, chill out!' if shout?(msg)
    return 'Fine. Be that way!' if silent?(msg)
    return 'Sure.' if question?(msg)
    'Whatever.'
  end

  private

  def shout?(msg)
    msg == msg.upcase && !msg.strip.empty?
  end

  def question?(msg)
    msg[-1, 1] == "?"
  end

  def silent?(msg)
   msg.strip.empty?
  end
end
