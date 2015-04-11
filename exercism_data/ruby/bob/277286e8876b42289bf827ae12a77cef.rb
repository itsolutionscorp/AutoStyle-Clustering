class Teenager  
  def rules
    raise NotImplementedError
  end

  def hey(comment)
    response(comment)
  end

  def response(comment)
    rules.each do |rule|
      return rule[1] if comment =~ rule[0]
    end
  end
end

class Bob < Teenager
  def rules
    [
      [/\A\s*\z/, "Fine. Be that way!"], # silence
      [/\A[^a-z]*\z/, "Woah, chill out!"], # ALL CAPS
      [/\?\z/, "Sure."], # Question
      [/./, "Whatever."] #catch all
    ]
  end
end

  #def hey(message)
  #  return "Fine. Be that way!" if silence?(message)
  #  return "Woah, chill out!" if upcase?(message)
  #  return "Sure." if question?(message)
  #  "Whatever."
  #end

  #private
  #  def upcase?(str)
  #    str == str.upcase
  #  end

  #  def question?(str)
  #    str.chars.last == '?'
  #  end

  #  def silence?(str)
  #    (str =~ /\A\s*\z/) != nil
  #  end
#end
