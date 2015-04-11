class WordProblem

  def initialize(math_question)
    @question = math_question
    @digits = all_digits.split(" ").count

    raise ArgumentError if too_complex?
  end

  def answer
    if digits == 2
      expression = two_digit_calculation 
    elsif digits == 3
      expression = three_digit_calculation 
    end

    eval(expression)
  end

  private 

  attr_reader :question, :digits

  def two_digit_calculation
    first, second = all_digits.split(" ")
    operator = math_operators[operator_as_word.first]
    [first, operator , second].join(" ")
  end

  def three_digit_calculation
    first, second, third = all_digits.split(" ")

    first_operator = math_operators[operator_as_word.first]
    second_operator = math_operators[operator_as_word[1]]

    ["(",first, first_operator, second, ")", second_operator , third].join(" ")
  end

  def all_digits
    question.gsub(/[^\d\-]/, ' ')
  end

  def math_operators
    {
      "plus" => "+",
      "minus" => "-",
      "multiplied" => "*",
      "divided" => "/"
    }
  end

  def too_complex?
    digits < 2
  end

  def operator_as_word
    operators = ["plus", "minus", "multiplied", "divided"]

    question.split(" ").select do |word|
      if operators.include? word
        word
      end
    end
  end

end
