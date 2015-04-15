class WordProblem
  attr_reader :problem

  def initialize(problem_input)
    @problem = problem_input
    validate(problem)
  end

  def answer
    sum = 0
    find_operators.each_with_index do |o, i|
      if i == 0
        sum += eval(parse_numbers[i] + o + parse_numbers[i+1])
      else
        sum = eval(sum.to_s + o + parse_numbers[i+1])
      end
    end
    sum
  end

  private

  def validate(problem)
    if problem_words.any?{|w| complex_operations.include?(w) }
      raise ArgumentError.new("NO!")
    end
  end

  def problem_words
    problem.split(" ")
  end

  def complex_operations
    ['cubed?', 'president']
  end

  def find_operators
    operator_words.map { |word| word_to_operator[word] }
  end

  def operator_words
    problem_words.select do |operator|
      word_to_operator.keys.include?(operator)
    end
  end

  def word_to_operator
    {
      "plus" => "+",
      "minus" => "-",
      "multiplied" => "*",
      "divided" => "/"
    }
  end

  def parse_numbers
    problem.scan(/\-?\d+/)
  end

  def equation
    parse_numbers.zip(find_operators).join
  end

end
