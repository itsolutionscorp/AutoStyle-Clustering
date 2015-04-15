class Bob
  def hey(str)
    return "Woah, chill out!" if is_shout?(str)
    return "Sure." if is_question?(str)
    return "Fine. Be that way!" if is_silence?(str)
    "Whatever."
  end

  def is_shout?(str)
    str.match(/[a-zA-Z]/) && str == str.upcase
  end

  def is_question?(str)
    str.match /\?\z/  
  end

  def is_silence?(str)
    str.strip == ''
  end
end
