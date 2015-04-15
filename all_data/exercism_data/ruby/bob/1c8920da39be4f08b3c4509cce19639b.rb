class Bob

  def hey(statement)
    statement = Statement.new(statement)
    case
      when statement.silence?
        'Fine. Be that way!'
      when statement.yelling?
        'Woah, chill out!' 
      when statement.question?
        'Sure.'
      else 
        'Whatever.'
    end
  end

end

class Statement
  attr_reader :statement

  def initialize(statement)
    @statement = statement
  end

  def silence?
    statement == '' || statement == '    '
  end

  def yelling?
    statement == statement.upcase
  end

  def question?
    statement.end_with?("?")
  end

end
