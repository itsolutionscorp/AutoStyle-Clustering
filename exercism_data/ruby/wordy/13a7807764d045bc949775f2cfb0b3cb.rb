class WordProblem
  attr_reader :question

  def initialize(question)
    @question = question
  end

  def answer
    template = clean_question(question)
    eval_template(template).to_i
  end

  def clean_question(question)
    question.gsub(/^What is (.*)\?$/, '\1')
  end

  def eval_template(template)
    template.dup.tap do |current|
      while !eval_complete?(current)
        eval_operators(current) or raise ArgumentError
      end
    end
  end

  def eval_complete?(template)
    template == template.to_i.to_s
  end

  def eval_operators(template)
    operators.any? do |word, symbol|
      template.gsub!(/^([-0-9]+) #{word} ([-0-9]+)/) do
        first, second = $1.to_i, $2.to_i
        first.send(symbol, second)
      end
    end
  end

  def operators
    {
      'plus'          => '+',
      'minus'         => '-',
      'multiplied by' => '*',
      'divided by'    => '/'
    }
  end
end
