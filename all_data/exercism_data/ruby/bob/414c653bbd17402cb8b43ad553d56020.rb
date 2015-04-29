class Bob
  RESPONSES = {
    question: "Sure.",
    yelling:  "Woah, chill out!",
    nothing:  "Fine. Be that way!",
    other:    "Whatever."
  }

  def hey(input)
    respond(input)
  end

  private

  def respond(input)
    response_type = find_input_type(input)
    RESPONSES[response_type]
  end

  def find_input_type(input)
    return :yelling  if shouting?(input)
    return :question if question?(input)
    return :nothing  if silence?(input)
    return :other
  end

  def shouting?(input)
    !input.match(/[A-Z]+\b/).nil? && input.match(/[a-z]+\b/).nil?

  end

  def question?(input)
    !(/^[\w\W]+\?\z/ =~ input).nil?
  end

  def silence?(input)
    (/\S/ =~ input).nil?
  end
end
