class Bob

  def initialize
    @grammar = Statement.new
  end

  def hey statement
    empty = ->(string) { @grammar.empty? string }
    shout = ->(string) { @grammar.shout? string }
    question = ->(string) { @grammar.question? string }

    case statement
    when empty then be_that_way
    when shout then chill_out
    when question then sure
    else whatever
    end
  end

  private
  def whatever
    'Whatever.'
  end

  def chill_out
    'Woah, chill out!'
  end

  def sure
    'Sure.'
  end

  def be_that_way
    'Fine. Be that way!'
  end
end

class Statement
  def question? statement
    statement.end_with? '?'
  end

  def shout? statement
    statement == statement.upcase
  end

  def empty? statement
    statement.nil? || statement.strip.empty?
  end
end
