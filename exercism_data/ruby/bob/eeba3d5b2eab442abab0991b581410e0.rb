class Bob
  def hey input
    statement = Statement.new input
    case
    when statement.shouting?  then 'Woah, chill out!'
    when statement.asking?    then "Sure."
    when statement.silent?    then 'Fine. Be that way!'
    else 'Whatever.'
    end
  end
end

class Statement
  def initialize input
    @input = input
  end
  def shouting?
    @input.match(/[a-zA-Z]/) && @input == @input.upcase
  end
  def asking?
    @input.end_with? "?"
  end
  def silent?
    @input.strip.empty?
  end
end
