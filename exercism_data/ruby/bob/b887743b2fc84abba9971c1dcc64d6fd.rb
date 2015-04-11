class Bob
  def hey(message)
    return 'Fine. Be that way.' if silence?(message)
    return 'Sure.' if question?(message)
    return 'Woah, chill out!' if yelling?(message)
    'Whatever.'
  end

  private
  def yelling?(str)
    !(str =~ /[a-z]/)
  end

  def question?(str)
    str =~ /\?\z/
  end

  def silence?(str)
    str.nil? || str.empty?
  end
end
