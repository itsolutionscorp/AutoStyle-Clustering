class Bob

  def hey(str)
    return "Woah, chill out!" if shout?(str)
    return "Sure." if question?(str)
    return "Fine. Be that way!" if silence?(str)
    return "Whatever."
  end

  private

  def question?(arg)
    arg.match(/[?]\z/)
  end

  def shout?(arg)
    arg == arg.upcase && arg.match(/[^0-9\W]/)
  end

  def silence?(arg)
    arg.match(/^\s*\z/)
  end
end
