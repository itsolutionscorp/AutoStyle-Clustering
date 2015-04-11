class Bob
  def hey(she_said)
    respond_with she_said
  end

  private

  def respond_with she_said
    begin
      return 'Fine. Be that way!' if silent? she_said
      return 'Woah, chill out!' if yelling? she_said
      return 'Sure.' if question? she_said
      raise
    rescue
      return 'Whatever.'
    end
  end

  def question? she_said
    she_said.end_with?('?')
  end

  def silent? she_said
    she_said.strip.empty?
  end

  def yelling? she_said
    she_said.upcase === she_said
  end
end
