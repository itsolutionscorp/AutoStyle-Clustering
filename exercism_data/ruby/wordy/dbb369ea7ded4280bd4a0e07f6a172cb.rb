class WordProblem
  
  OPERATOR_REGEX  = /plus|minus|divided\sby|multiplied\sby/
  POSITIVE_OR_NEGATIVE_REGEX =  /-?\d+|\d+/
  OPERATORS = {
    :+ => "plus", 
    :- => "minus", 
    :/ => "divided\sby", 
    :* => "multiplied\sby"
  }

  attr_reader :question

  def initialize(question)
    @question = question
    raise ArgumentError unless valid?
  end

  def answer
    recursive_answer(convert_operators, 0, 2, nil)
  end

  private
  def recursive_answer(array, x, y, answer)
    return answer if y >= array.length

    calc     = array[(x..y)]
    answer ||= calc[0]
    answer   = answer.send(calc[1], calc[2])

    recursive_answer(array, x+2, y+2, answer)
  end

  def to_a
    question.scan(/#{POSITIVE_OR_NEGATIVE_REGEX}|#{OPERATOR_REGEX}/)
  end

  def convert_operators
    to_a.map { |x| OPERATORS.key(x) || x.to_i }
  end

  def valid?
    !question.scan(/#{OPERATOR_REGEX}/).empty?
  end

end
