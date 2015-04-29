class Bob
  def hey(message=nil)
    return "Fine. Be that way!" if silent?(message)
    return "Woah, chill out!"   if yelled?(message)
    return "Sure."              if question?(message)
    "Whatever."
  end

  private

  def question?(msg)
    msg.strip =~ /[?⁇⁉⍰﹖？︖]\Z/
  end

  def yelled?(msg)
    msg.upcase == msg
  end

  def silent?(msg)
    return true if msg.nil?
    msg.strip =~ /\A[ ]*\Z/
  end
end
