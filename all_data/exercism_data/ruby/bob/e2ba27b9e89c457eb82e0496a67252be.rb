class Bob
  def hey(statement)
    return 'Fine. Be that way.' if missing_content?(statement)
    return 'Woah, chill out!' if shouting?(statement)
    return 'Sure.' if asking?(statement)

    'Whatever.'
  end

  def missing_content?(statement)
    statement.nil? || statement.empty?
  end

  def shouting?(statement)
    statement == statement.upcase
  end

  def asking?(statement)
    statement.end_with?('?')
  end
end
