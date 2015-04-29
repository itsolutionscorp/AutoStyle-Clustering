class Bob

  def hey(input)
    statement = Statement.new(input)
    if statement.silence?
      "Fine. Be that way!"
    elsif statement.yelling?
      "Whoa, chill out!"
    elsif statement.question?
      "Sure."
    else
      "Whatever."
    end
  end

  # Internal: This wraps up a statement made to Bob and is responsible for
  # deciding his reaction
  class Statement
    attr_reader :input

    def initialize(input)
      @input = input
    end

    # Public: is this statement a question?
    #
    # Returns a boolean
    def question?
      input.end_with? "?"
    end

    # Public: is this statement shouted?
    #
    # Returns a boolean
    def yelling?
      input.upcase == input && input =~ /[A-Z]/
    end

    # Public: is this a silent statement?
    #
    # Returns a boolean
    def silence?
      input =~ /\A\s*\z/
    end
  end
end
