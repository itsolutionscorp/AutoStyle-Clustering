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

  def hey(statement)
    if TextClassifier.is_silent?(statement)
      'Fine. Be that way!'
    elsif TextClassifier.is_shouting?(statement)
      'Woah, chill out!'
    elsif TextClassifier.is_question?(statement)
      'Sure.'
    else
      "Whatever."
    end
  end

end
