class WordProblem
  attr_reader :question
  def initialize question
    @question = question
  end
  
  def answer
    raise ArgumentError.new("I do not understand: '#{question}'") if invalid_question?
    unless @answer
      @answer = number[0].send(action[0], number[1])
      @answer = @answer.send(action[1], number[2]) if chained?
    end
    @answer
  end
  
  private
  def valid_actions
    @valid_actions ||= {
      "plus"=>:+,
      "minus"=>:-,
      "multiplied by"=>:*,
      "divided by"=>:/
    }
  end
  
  
  def invalid_question?
    matches.nil?
  end
  
  def matches
    @matches ||= question.match pattern
  end
  
  def pattern
    operations = valid_actions.keys.join('|')
    number = '-?\d+'
    /what is (#{number}) (#{operations}) (#{number})( (#{operations}) (#{number}))?\?/i
  end
  
  def chained?
    !matches[4].nil?
  end
  
  def action
    @action ||= [valid_actions[matches[2]],valid_actions[matches[5]]]
  end
  
  def number
    @number ||= [matches[1],matches[3],matches[6]].map(&:to_i)
  end
end
