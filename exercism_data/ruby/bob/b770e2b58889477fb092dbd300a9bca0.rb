class Bob

  attr_reader :statement_classifier

  def initialize(statement_classifier=Statement)
    @statement_classifier = statement_classifier
  end

  def hey(phrase)
    statement = statement_classifier.new(phrase)

    case
    when statement.silence?
      "Fine. Be that way!"
    when statement.shouted?
      "Whoa, chill out!"
    when statement.question?
      "Sure."
    else
      "Whatever."
    end

  end

end

class Statement

  attr_reader :phrase
  def initialize(phrase)
    @phrase = phrase
  end

  def shouted?
    phrase.upcase == phrase && !only_special_characters
  end

  def question?
    phrase.end_with?("?")
  end

  def silence?
    phrase.strip.empty?
  end

  private

  def only_special_characters
    phrase.upcase == phrase.downcase
  end

end
