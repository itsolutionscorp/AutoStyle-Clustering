class Bob
  def hey(statement)
    conversation = Conversation.new statement
    return 'Fine. Be that way!' if conversation.silent?
    return 'Woah, chill out!' if conversation.shouting?
    return 'Sure.' if conversation.question?
    'Whatever.'
  end
end

class Conversation

  def initialize(statement)
    @statement = statement
  end

  def shouting?
    @statement == @statement.upcase
  end

  def question?
    @statement[-1] == '?'
  end

  def silent?
    @statement.strip.empty?
  end
end
