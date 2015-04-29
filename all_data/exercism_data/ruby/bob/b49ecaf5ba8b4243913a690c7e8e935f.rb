class Bob

  def hey arg1
    return 'Fine. Be that way!' if silence?(arg1)
    return 'Woah, chill out!' if shouting(arg1)
    return 'Sure.' if question?(arg1)
    'Whatever.'
  end

  def silence?(arg1)
    arg1.nil? or arg1.strip.empty?
  end

  def shouting(arg1)
    arg1 == arg1.upcase
  end

  def question?(arg1)
    arg1[-1] == '?'
  end

end
