class Bob
  attr_accessor :response
  def initialize(response = TeenagerResponse)
    self.response = response
  end

  def hey(query)
    response.to(StatementParser.parse(query))
  end
end

class TeenagerResponse
  def self.to(statement)
    self.new(statement).say
  end

  def initialize(statement)
    self.statement = statement
  end

  def say
    self.send("to_#{statement.class.to_s.downcase}".to_sym)
  end

  def method_missing(method_name, *arguments, &block)
    if method_name.to_s =~ /to_(.*)/
      to_everything
    else
      super
    end
  end

  private

  def to_silence
    "Fine. Be that way!"
  end

  def to_yelling
    "Woah, chill out!"
  end

  def to_question
    "Sure."
  end

  def to_everything
    "Whatever."
  end

  attr_accessor :statement

end

class StatementParser
  def self.parse(statement)
    self.new(statement).parse
  end

  def initialize(statement)
    self.statement = statement
  end

  def parse
    if silence?
      Silence.new
    elsif yelling?
      Yelling.new
    elsif question?
      Question.new
    else
      NullStatement.new
    end
  end

  private

  def silence?
    statement.strip.empty?
  end

  def yelling?
    statement.upcase == statement && statement.downcase != statement
  end

  def question?
    statement[-1..-1] == "?"
  end

  attr_accessor :statement
end

class NullStatement
end

class Question
end

class Yelling
end

class Silence
end
