class Conversationalist
  def hey(statement)
    statement = Statement.new(statement)
    respond_to statement
  end

  def respond_to statement
    responses.map { |r| r.call(statement) }.compact.first
  end

  def responses
    # Not much of a conversationalist.
    [-> statement { statement }]
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

class Bob < Conversationalist
  def responses
    feelings.values
  end

  def feelings
    {
      snooty:      -> statement { "Fine. Be that way!" if statement.silence? },
      offended:    -> statement { "Woah, chill out!" if statement.shouting? },
      noncommital: -> statement { "Sure." if statement.question? },
      teenager:    -> statement { "Whatever." }
    }
  end
end
