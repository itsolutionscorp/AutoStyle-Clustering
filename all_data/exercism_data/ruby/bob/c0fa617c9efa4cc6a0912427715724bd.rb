module TextClassifier

  def self.silent?(statement)
    statement.to_s.strip.empty?
  end

  def self.shouting?(statement)
    statement.upcase == statement
  end

  def self.question?(statement)
    statement[-1] == '?'
  end

end

class Bob

  def initialize(classifier=TextClassifier)
    @classifier = classifier
  end

  def hey(statement)
    if @classifier.silent?(statement)
      'Fine. Be that way!'
    elsif @classifier.shouting?(statement)
      'Woah, chill out!'
    elsif @classifier.question?(statement)
      'Sure.'
    else
      "Whatever."
    end
  end

end
