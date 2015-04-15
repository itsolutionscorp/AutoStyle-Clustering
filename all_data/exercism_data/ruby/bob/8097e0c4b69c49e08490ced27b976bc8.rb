class Bob
  def hey(message)
    return 'Fine. Be that way!' if silent?(message)
    return 'Woah, chill out!' if shouting?(message)
    return 'Sure.' if question?(message)
    'Whatever.'
  end

  private

  def silent?(msg)
    msg.nil? || msg.empty?
  end

  def shouting?(msg)
    msg.upcase == msg
  end

  def question?(msg)
    msg.end_with?('?')
  end
end
