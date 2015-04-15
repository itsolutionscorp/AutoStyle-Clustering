class Bob
  class Responder
    attr_reader :statement

    def self.respond_to(statement)
      new(statement).response
    end

    def initialize(statement)
      @statement = statement
    end

    def response
      responses.find { |condition, response|
        condition.call
      }.last
    end

    private

    def responses
      {
        lambda { statement.empty? } => "Fine. Be that way.",
        lambda { statement.question? } => "Sure.",
        lambda { statement.shouting? } => "Woah, chill out!",
        lambda { true } => "Whatever."
      }
    end
  end

  class Statement < String
    def shouting?
      !empty? && upcase == self
    end

    def question?
      end_with?("?")
    end
  end

  def hey(statement)
    Responder.respond_to(Statement.new(statement.to_s))
  end
end
