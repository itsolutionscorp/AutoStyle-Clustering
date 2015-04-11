class Bob

  attr_reader :input

  def hey(input)
    @input = input

    respond_to_silence ||
    respond_to_shouting ||
    respond_to_question ||
    respond_to_statement
  end

  private

  def respond_to_silence
    "Fine. Be that way!" if silent?
  end

  def respond_to_shouting
    "Woah, chill out!" if shouting?
  end

  def respond_to_question
    "Sure." if question?
  end

  def respond_to_statement
    "Whatever."
  end

  def silent?
    input.strip.empty?
  end

  def shouting?
    input.upcase == input
  end

  def question?
    input.end_with?('?')
  end

end
