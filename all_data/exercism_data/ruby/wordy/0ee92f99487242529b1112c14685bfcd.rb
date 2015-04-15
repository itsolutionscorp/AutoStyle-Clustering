class WordProblem
  def initialize(question)
    @question = question
  end

  def answer
    fail ArgumentError if invalid?

    ans = n1.send(op1, n2)
    ans = ans.send(op2, n3) unless op2.nil?

    ans
  end

  private

  def matches
    @matches ||= @question.match(pattern)
  end

  def invalid?
    matches.nil?
  end

  def pattern
    ops = '(plus|minus|multiplied by|divided by)'
    /What is (-?\d+) #{ops} (-?\d+)( #{ops} (-?\d+))?\?/
  end

  def n1
    matches[1].to_i
  end

  def n2
    matches[3].to_i
  end

  def n3
    matches[6].to_i
  end

  def op1
    operations[matches[2]]
  end

  def op2
    operations[matches[5]]
  end

  def operations
    {
      'plus'          => :+,
      'minus'         => :-,
      'multiplied by' => :*,
      'divided by'    => :/
    }
  end
end
