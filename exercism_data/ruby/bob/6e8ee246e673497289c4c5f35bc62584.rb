class Bob
  def hey(statement)
    statement = Statement.new(statement)
    case
    when statement.silence?  then "Fine. Be that way!"
    when statement.shouting? then "Woah, chill out!"
    when statement.question? then "Sure."
    else                          "Whatever."
    end
  end

  class Statement
    attr_reader :statement

    def initialize(statement)
      @statement = statement.to_s
    end

    def silence?
      statement.strip.empty?
    end

    def question?
      statement.end_with? '?'
    end

    def shouting?
      statement.upcase == statement && statement =~ /[a-z]/i
    end
  end
end
