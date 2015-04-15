class Bob
  def hey(greeting)
    return "Fine. Be that way!" if !/[[:word:]]/.match(greeting)
    if !/[a-zA-Z]/.match(greeting)
      return /\?\z/.match(greeting) ? "Sure." : "Whatever."
    end
    return "Whoa, chill out!" if !/[a-z]/.match(greeting)
    return "Sure." if /\?\z/.match(greeting)
    "Whatever."
  end
end
