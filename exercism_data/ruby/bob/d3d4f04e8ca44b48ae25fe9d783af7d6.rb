class Bob
  def hey(greeting)
    @text = greeting
    return "Fine. Be that way!" if is_empty?
    return "Whoa, chill out!" if its_a_yell?
    return "Sure." if its_a_qestion?
    "Whatever."
  end

  def is_empty?
    !/[[:word:]]/.match(@text)
  end

  def its_a_qestion?
    /\?\z/.match(@text)
  end

  def its_a_yell?
     !/[a-z]/.match(@text) & /[A-Z]/.match(@text)
  end
end
