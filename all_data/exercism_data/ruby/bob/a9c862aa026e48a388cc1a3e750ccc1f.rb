class Bob
  attr_reader :statement

  def hey(str)
    @statement = str
    return 'Fine. Be that way!' if said_nothing?
    return "Woah, chill out!" if shouting?
    return "Sure." if asking_question?
    'Whatever.'
  end

  private

  def shouting?
    statement =~ /[a-zA-Z]/ && statement.upcase == statement
  end

  def asking_question?
    statement.end_with?("?")
  end

  def said_nothing?
    statement.strip.empty?
  end

end
