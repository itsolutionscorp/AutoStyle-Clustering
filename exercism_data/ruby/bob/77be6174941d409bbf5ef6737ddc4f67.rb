class Bob
  def hey statement
    message = Statement.new statement
    answer message
  end

  private
  def answer message
    empty = ->(statement) { statement.empty? }
    shout = ->(statement) { statement.shout? }
    question = ->(statement) { statement.question? }

    case message
    when empty then be_that_way
    when shout then chill_out
    when question then sure
    else whatever
    end
  end

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

Statement = Struct.new(:string) do
  def question?
    string.end_with? '?'
  end

  def shout?
    string == string.upcase
  end

  def empty?
    string.nil? || string.strip.empty?
  end
end
