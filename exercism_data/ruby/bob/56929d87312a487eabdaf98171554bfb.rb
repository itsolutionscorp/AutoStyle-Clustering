class Bob
  def hey(statement)
    what_was_said = Statement.new(statement)
    return "Fine. Be that way." if what_was_said.silence?
    return "Woah, chill out!" if what_was_said.shouting?
    return "Sure." if what_was_said.asking?
    "Whatever."
  end
end

class Statement < String
  def initialize(object)
    object = object.to_s
    super
  end

  def shouting?
    self == self.upcase
  end

  def asking?
    self.end_with?("?")
  end

  def silence?
    self == ""
  end
end
