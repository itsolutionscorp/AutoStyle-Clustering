class Bob
  def hey(statement)
    they = Phrase.new(statement)
    return "Fine. Be that way!" if they.said_nothing
    return "Woah, chill out!" if they.screamed
    return "Sure." if they.asked_a_question
    "Whatever."
  end

  class Phrase
    attr_reader :statement

    def initialize(statement)
      @statement = statement
    end

    def said_nothing
      statement.to_s.strip.empty?
    end

    def screamed
      statement == statement.upcase
    end

    def asked_a_question
      statement.end_with? "?"
    end
  end
end
