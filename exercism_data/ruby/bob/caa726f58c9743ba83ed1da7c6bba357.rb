class Bob

  def hey phrase
   return 'Fine. Be that way!' if phrase.silence?
   return "Woah, chill out!" if phrase.yelling?
   return "Sure." if phrase.questioning?
    "Whatever."
  end
end

class String
  def yelling?
    self.upcase == self and self =~ /[a-z]/i
  end

  def questioning?
    self.end_with?("?")
  end

  def silence?
    self.strip.empty?
  end
end
