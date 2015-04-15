class WordProblem
  def initialize question
    @question = question
  end

  def answer
    evaluate(expression)
  rescue SyntaxError, NameError
    raise ArgumentError, "Invalid question"
  end

  def evaluate string
    elements = string.scan(/\S+/)
    res = elements.shift
    elements.each_slice(2).inject(res) do |memo, (operator, value)|
      eval [memo, operator, value].join(' ')
    end
  end

  private def expression
    @question
      .gsub(/What is |\?$/,'')
      .gsub(/plus/,'+')
      .gsub(/minus/,'-')
      .gsub(/multiplied\s+by/,'*')
      .gsub(/divided\s+by/,'/')
  end
end
