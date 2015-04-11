class Bob
  def hey(statement)
    statement = Statement.new({statement: statement})
    case
    when statement.silence?
      'Fine. Be that way!'
    when statement.shout?
      'Woah, chill out!'
    when statement.question?
      'Sure.'
    else
      'Whatever.'
    end
  end
end

class Statement < String
  attr_reader :statement

  def initialize(args={})
    @statement = args[:statement]
  end

  def silence?
    statement.to_s.empty?
  end

  def shout?
    statement == statement.upcase 
  end

  def question?
    statement.end_with?('?')
  end
end
