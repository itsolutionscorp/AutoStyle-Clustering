class Bob
  def hey(remark)
    statement = StatementFactory.build(remark)
    send "respond_to_#{statement.name.downcase}"
  end

  def method_missing(*)
    'Whatever.'
  end

  private

  def respond_to_shouting
    'Whoa, chill out!'
  end

  def respond_to_silence
    'Fine. Be that way!'
  end

  def respond_to_question
    'Sure.'
  end
end

class Shouting
  def self.match?(remark)
    remark.upcase == remark && remark.downcase != remark
  end
end

class Silence
  def self.match?(remark)
    remark.strip.empty?
  end
end

class Question
  def self.match?(remark)
    remark.end_with? '?'
  end
end

class Default
  def self.match?(remark)
    true
  end
end

class StatementFactory
  STATEMENT_TYPES = [Shouting,
                     Silence,
                     Question,
                     Default]

  def self.build(remark)
    STATEMENT_TYPES.detect{ |klass| klass.match? remark }
  end
end
