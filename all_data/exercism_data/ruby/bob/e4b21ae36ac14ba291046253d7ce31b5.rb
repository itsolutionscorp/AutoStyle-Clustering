class Bob
  def hey(statement)
    return 'Fine. Be that way.' if missing_content?(statement)
    return 'Woah, chill out!' if is_shouting?(statement)
    return 'Sure.' if is_question?(statement)

    'Whatever.'
  end

  def missing_content?(statement)
    statement.nil? || statement.empty?
  end

  def is_shouting?(statement)
    statement == statement.upcase
  end

  def is_question?(statement)
    statement.end_with?('?')
  end
end
