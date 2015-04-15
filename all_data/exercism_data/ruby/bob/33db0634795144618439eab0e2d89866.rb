require 'pry'

class Bob

  def hey(remark)
    interpretation = interpret(remark)
    response_map[interpretation]
  end

  private

  def interpret(remark)
    StatementType.new(remark).call
  end

  def response_map
    map = {
      silence: 'Fine. Be that way!',
      yelling: 'Whoa, chill out!',
      question: 'Sure.',
      statement: 'Whatever.'
    }
  end

end

module StringReader
  def initialize(string)
    @string = string
  end
end

class StatementType
  include StringReader

  def call

    if silence?
      :silence
    elsif yelling?
      :yelling
    elsif question?
      :question
    else
      :statement
    end

  end

  private

  def silence?
    SilenceTest.new(@string).call
  end

  def yelling?
    YellingTest.new(@string).call
  end

  def question?
    QuestionTest.new(@string).call
  end

end

class SilenceTest
  include StringReader

  def call
    test = @string.strip
    test.empty?
  end

end

class YellingTest
  include StringReader

  def call
    has_capitalized? && all_caps?
  end

  private

  def has_capitalized?
    @string.scan(/[A-Z]/).count > 0
  end

  def all_caps?
    @string.upcase == @string
  end

end

class QuestionTest
  include StringReader

  def call
    @string.chars.last == '?'
  end

end
