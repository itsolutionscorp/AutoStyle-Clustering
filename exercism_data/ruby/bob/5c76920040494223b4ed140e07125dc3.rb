class Bob
  def hey statement
    reply_to Statement.new(statement)
  end

  private

  REPLY_TO = {
    question: "Sure.",
    force: "Woah, chill out!",
    silence: "Fine. Be that way!",
    default: "Whatever."
  }

  def reply_to statement
    REPLY_TO[statement.type]
  end
end

class Statement
  def initialize input
    @input = input
  end

  def type
    if silent?
      :silence
    elsif force?
      :force
    elsif question?
      :question
    else
      :default
    end
  end

  private

  def question?
    @input.end_with? "?"
  end

  def force?
    return unless @input.match(/[a-zA-Z]/)
    @input.upcase == @input
  end

  def silent?
    @input.to_s.strip.empty?
  end
end
