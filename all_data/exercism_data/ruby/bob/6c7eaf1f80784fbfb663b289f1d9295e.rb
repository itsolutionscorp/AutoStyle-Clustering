class Bob
  def hey(statement)
    return 'Fine. Be that way!' if statement.strip.empty?
    return 'Woah, chill out!' if statement == statement.upcase
    return 'Sure.' if statement.end_with?('?')
    'Whatever.'
  end
end
