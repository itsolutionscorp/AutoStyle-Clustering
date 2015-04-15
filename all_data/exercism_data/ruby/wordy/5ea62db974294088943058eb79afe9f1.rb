class WordProblem
  def initialize(question)
    @question = question
  end

  def answer
    raise ArgumentError if bad_expression?

    instance_eval(expression)
  end

  private

  attr_reader :question

  def expression
    question.gsub(/What is /, '')
      .gsub(/\?/, '')
      .gsub(/plus/, '+')
      .gsub(/minus/, '-')
      .gsub(/multiplied by/, '*')
      .gsub(/divided by/, '/')
      .gsub(/\d+/, '\0)')
      .tap { |exp| exp.prepend('(' * exp.scan(')').size) }
  end

  def bad_expression?
    expression =~ /[^\d\s\+\-\*\/\(\)]/
  end
end
