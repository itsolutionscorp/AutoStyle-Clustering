module TextClassifier

  def self.is_silent?(statement)
    statement.to_s.strip.empty?
  end

  def self.is_shouting?(statement)
    statement.upcase == statement
  end

  def self.is_question?(statement)
    statement[-1] == '?'
  end

end

class Bob

  def initialize(classifier=TextClassifier)
    @classifier = classifier
  end

  def hey(statement)
    if @classifier.is_silent?(statement)
      'Fine. Be that way!'
    elsif @classifier.is_shouting?(statement)
      'Woah, chill out!'
    elsif @classifier.is_question?(statement)
      'Sure.'
    else
      "Whatever."
    end
  end

end
