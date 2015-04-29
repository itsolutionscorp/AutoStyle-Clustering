class WordProblem
  MATH_OPERATORS = {
    "plus" => "+",
    "minus" => "-",
    "multiplied" => "*",
    "divided" => "/",
  }

  def initialize(math_question)
    @math_question = math_question
    @digits = all_digits.split(" ").count

    raise ArgumentError if too_complex?
  end

  def answer
    expression = if digits == 2
      two_digit_calculation
    elsif digits == 3
      three_digit_calculation
    end

    eval(expression)
  end

  private

  attr_reader :math_question, :digits

  def two_digit_calculation
    first, second = all_digits.split(" ")
    operator = MATH_OPERATORS[operator_as_word.first]
    [first, operator , second].join(" ")
  end

  def three_digit_calculation
    first, second, third = all_digits.split(" ")
    first_operator = MATH_OPERATORS[operator_as_word.first]
    second_operator = MATH_OPERATORS[operator_as_word[1]]
    ["(",first, first_operator, second, ")", second_operator , third].join(" ")
  end

  def all_digits
    math_question.gsub(/[^\d\-]/, ' ')
  end

  def too_complex?
    digits < 2
  end

  def operator_as_word
    operators = ["plus", "minus", "multiplied", "divided"]
    math_question.split(" ").select do |word|
      operators.include?(word)
    end
  end
end
