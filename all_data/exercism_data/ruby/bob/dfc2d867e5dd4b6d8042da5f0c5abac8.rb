class BobParser
  def initialize(statement)
    @statement = statement.to_s
  end

  def silent?
    @statement.strip == ''
  end

  def question?
    @statement.end_with? "?"
  end

  def yelling?
    @statement.upcase == @statement && @statement.to_i.to_s != @statement
  end
end

class Bob
  def hey(statement)
    bob_parser = BobParser.new(statement)

    if bob_parser.silent?
      "Fine. Be that way!"
    elsif bob_parser.yelling?
      "Woah, chill out!"
    elsif bob_parser.question?
      "Sure."
    else
      "Whatever."
    end
  end
end
