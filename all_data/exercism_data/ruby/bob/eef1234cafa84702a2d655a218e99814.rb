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
      (find_response || default_response).last
    end

    private

    def default_response
      [true, "Whatever."]
    end

    def find_response
      responses.find { |condition, response|
        condition.call
      }
    end

    def responses
      {
        lambda { statement.all_caps? } => "Woah, chill out!",
        lambda { statement.blank? } => "Fine. Be that way.",
        lambda { statement.question? } => "Sure."
      }
    end
  end

  class Statement < String
    def all_caps?
      !blank? && upcase == self
    end

    def blank?
      self == ""
    end

    def question?
      self[-1] == "?"
    end
  end

  def hey(statement)
    Responder.respond_to(Statement.new(statement || ""))
  end
end
