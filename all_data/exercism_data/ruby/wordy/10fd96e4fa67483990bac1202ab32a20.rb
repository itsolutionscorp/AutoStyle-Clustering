class WordProblem
  attr_reader :text

  def initialize(text)
    @text = text
    validate_operators
    validate_numbers
  end

  def answer
    ops = operators
    numbers.drop(1).each.reduce(numbers.first) do |a,b|
      operator = ops.shift
      calculate.fetch(operator).call(a,b)
    end
  end

  def operators
    @operators ||= text.scan(/plus|minus|multiplied|divided/).map(&:to_sym)
  end

  def numbers
    @numbers ||= text.scan(/-?\d+/).map(&:to_i)
  end

  def calculate
    {
      :plus       => lambda { |a,b| a + b },
      :minus      => lambda { |a,b| a - b },
      :multiplied => lambda { |a,b| a * b },
      :divided    => lambda { |a,b| a / b }
    }
  end

  private

  def validate_operators
    raise ArgumentError if operators.empty?
  end

  def validate_numbers
    raise ArgumentError if numbers.empty?
  end
end
