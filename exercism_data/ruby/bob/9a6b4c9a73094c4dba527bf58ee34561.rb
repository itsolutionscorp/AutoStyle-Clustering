class Bob
  def hey(statement)
    return 'Fine. Be that way!' if statement =~ /\A\s*\z/
    return 'Woah, chill out!' if statement == statement.upcase
    return 'Sure.' if statement =~ /\?\z/
    'Whatever.'
  end
end
