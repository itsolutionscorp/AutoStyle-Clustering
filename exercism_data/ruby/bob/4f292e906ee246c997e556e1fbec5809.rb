class Bob
  def hey(sentence)
    reply_to Statement.new(sentence)
  end

  private
    def reply_to(statement)
      case
      when statement.meaningless? then "Fine. Be that way."
      when statement.asking?      then "Sure."
      when statement.shouting?    then "Woah, chill out!"
      else
        "Whatever."
      end
    end
end

class Statement
  attr_reader :sentence

  def initialize(sentence)
    @sentence = String(sentence)
  end

  def meaningless?
    sentence.empty?
  end

  def asking?
    sentence.end_with?("?")
  end

  def shouting?
    sentence == sentence.upcase
  end
end
