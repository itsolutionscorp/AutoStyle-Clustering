class Bob
  def hey statement
    respond_to Statement.new(statement)
  end

  private

  RESPOND_TO = {
    question: "Sure.",
    force: "Woah, chill out!",
    silence: "Fine. Be that way!",
    default: "Whatever."
  }

  def respond_to statement
    RESPOND_TO[statement.type]
  end
end

class Statement
  def initialize input
    @input = input
  end

  def type
    return :silence if silent?
    return :force if force?
    return :question if question?
    :default
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
