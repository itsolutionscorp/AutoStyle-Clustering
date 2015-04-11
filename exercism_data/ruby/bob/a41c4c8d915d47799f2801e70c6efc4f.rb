class Bob
  def hey(statement)
    statement = Statement.new(statement)

    case
      when statement.muting?
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
  def initialize(statement)
    @statement = statement
  end

  def muting?
    @statement.strip.empty?
  end

  def yelling?
    @statement == @statement.upcase
  end

  def question?
    @statement.end_with? '?'
  end
end
