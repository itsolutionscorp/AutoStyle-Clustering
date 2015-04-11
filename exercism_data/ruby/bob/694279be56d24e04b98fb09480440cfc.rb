class Bob
  def hey(greeting)
    @greeting = greeting
    return "Fine. Be that way!" if silent?
    return "Woah, chill out!" if shouting?
    return "Sure." if question?
    "Whatever."
  end

  private

  def shouting?
    @greeting.match(/[A-Z]+/) && !@greeting.match(/[a-z]+/)
  end

  def question?
    @greeting.match(/\?\z/)
  end

  def silent?
    @greeting.strip.empty?
  end
end
