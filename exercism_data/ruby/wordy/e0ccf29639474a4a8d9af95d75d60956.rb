class WordProblem

  attr_reader :question

  def initialize(question)
    @question = question
  end

  def answer
    raise ArgumentError if parts.empty?
    digits = question.scan(/-?\d+/).map(&:to_i)
    sum = [] << digits.shift
    parts.each do |part|
      sum << digits.shift
      sum = [sum.inject(operator(part))]
    end
    sum[0]
  end

  def parts
    question[0..-2].split(/-?\d+/)[1..-1]
  end

  def operator part
    case part
    when /plus/
      :+
    when /minus/
      :-
    when /multiplied/
      :*
    when /divided/
      :/
    else
      raise ArgumentError
    end
  end
end
