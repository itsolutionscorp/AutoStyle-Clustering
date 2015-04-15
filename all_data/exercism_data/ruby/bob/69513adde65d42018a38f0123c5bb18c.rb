class Bob
  def hey(statement)
    statement = Statement.new(statement)

    if statement.silence?
      'Fine. Be that way!'
    elsif statement.yell?
      'Woah, chill out!'
    elsif statement.question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  class Statement
    def initialize(statement)
      @statement = statement.to_s
    end

    def silence?
      statement.strip.empty?
    end

    def yell?
      statement == statement.upcase
    end

    def question?
      statement.end_with? '?'
    end

    private

    attr_reader :statement
  end
end
