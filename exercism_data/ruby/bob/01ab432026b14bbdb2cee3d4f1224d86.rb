# Overkill to open this core class, just having fun.
class String
  def question?
    self[-1] == '?'
  end

  def command?
    self == self.upcase
  end

  def silence?
    self.strip.empty?
  end
end

class Teen
  def hey(txt)
    return  "Fine. Be that way!" if !txt || txt.silence?
    return "Woah, chill out!" if txt.command?
    return "Sure." if txt.question?
   
    "Whatever." # Statement.
  end
end

# Should just be an instance, not a subclass.
class Bob < Teen
end
