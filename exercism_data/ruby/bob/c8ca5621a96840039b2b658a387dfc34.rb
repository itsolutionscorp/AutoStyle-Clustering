class Bob

  attr_accessor :statement
  REPLIES = %w[silence shouting question statement]

  def hey(statement)
    self.statement = statement
    answer
  end

  private

  def answer
    answer = REPLIES.find { |reply| send(reply + '?') }
    send(answer + '_reply')
  end
    
  def shouting?
    statement.upcase == statement
  end

  def shouting_reply
    shouting? && 'Woah, chill out!'
  end
  
  def question?
    statement.end_with? '?'
  end

  def question_reply
    question? && 'Sure.'
  end

  def silence?
    statement.to_s.strip.empty?
  end

  def silence_reply
    silence? && 'Fine. Be that way!'
  end

  def statement?
    true
  end

  def statement_reply
    'Whatever.'
  end

end
