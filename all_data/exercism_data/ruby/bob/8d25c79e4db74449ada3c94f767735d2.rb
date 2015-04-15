class Bob
  def hey(statement)
    @statement = statement
    answer
  end

  def answer
    return 'Fine. Be that way!' if spaced?
    return 'Woah, chill out!' if screamed?
    return 'Sure.' if questioned?
    'Whatever.'
  end

  private

  def spaced?
    @statement.strip.size == 0
  end

  def screamed?
    @statement[/\S+/].upcase == @statement[/\S+/]
  end

  def questioned?
    !!@statement[/\?$/]
  end
end
