class WordProblem
  attr_reader :question_string
  def initialize(question_string)
    @question_string = question_string
  end

  def answer
    raise ArgumentError if components.length < 3

    first, *rest = *components
    rest.each_slice(2).reduce(first.to_i) do |total, (operation, operand)|
      total.send( words_to_operation[operation], operand.to_i)
    end
  end

  private

  def words_to_operation
    {
      'plus' => :+,
      'minus' => :-,
      'multiplied' => :*,
      'divided' => :/
    }
  end

  def components
    question_string.split(/[ \?]/).select do |component|
      begin
        Integer(component)
      rescue
        ['plus', 'minus', 'multiplied', 'divided'].include?(component)
      end
    end
  end
end
