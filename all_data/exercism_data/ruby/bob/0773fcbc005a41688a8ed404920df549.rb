class Bob
  class Statement
    def initialize(text)
      @text = text.to_s
    end

    def say_anything?
      !@text.empty?
    end

    def yelling?
      @text == @text.upcase
    end

    def question?
      @text.end_with?("?")
    end
  end

  def hey(statement)
    statement = Statement.new(statement)
    return "Fine. Be that way!" unless statement.say_anything?
    return "Woah, chill out!" if statement.yelling?
    return "Sure." if statement.question?
    "Whatever."
  end
end
