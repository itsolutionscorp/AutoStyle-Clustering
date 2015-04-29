class Bob
  def hey(statement)
    return 'Fine. Be that way.' if silence?(statement)
    return 'Sure.' if question?(statement)
    return 'Woah, chill out!' if yelling?(statement)
    'Whatever.'
  end

  private
  def yelling?(statement)
    !(statement =~ /[a-z]/)
  end

  def question?(statement)
    statement =~ /\?\z/
  end

  def silence?(statement)
    statement.nil? || statement.empty?
  end
end
