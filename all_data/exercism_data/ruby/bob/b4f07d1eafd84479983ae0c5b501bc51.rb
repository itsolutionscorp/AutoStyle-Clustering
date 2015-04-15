class Bob
  def hey(phrase)
    phrase = Phrase.new(phrase)
    if phrase.quiet?
      "Fine. Be that way."
    elsif phrase.shouting?
      "Woah, chill out!"
    elsif phrase.asking?
      "Sure."
    else
      "Whatever."
    end
  end

  class Phrase
    def initialize(statement)
      @statement = statement
    end

    def quiet?
      @statement.nil? || @statement.to_s.strip.empty?
    end

    def shouting?
      @statement == @statement.upcase
    end

    def asking?
      @statement.to_s.end_with? '?'
    end

  end

end
