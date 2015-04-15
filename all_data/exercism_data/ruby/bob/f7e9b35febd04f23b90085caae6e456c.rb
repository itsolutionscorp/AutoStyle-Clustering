class Bob
  def hey(greeting)
    @text = greeting
    return "Fine. Be that way!" if isEmpty?
    return "Whoa, chill out!" if itsAYell?
    return "Sure." if itsAQestion?
    return "Sure." if itsAQestion?
    "Whatever."
  end

  def isEmpty?
    !/[[:word:]]/.match(@text)
  end

  def itsAQestion?
    /\?\z/.match(@text)
  end

  def itsAYell?
     !/[a-z]/.match(@text) & /[A-Z]/.match(@text)
  end
end
