class Bob

  REPLIES = %w[silence shouting question statement]
  SHOUTING_REPLY = 'Woah, chill out!'
  QUESTION_REPLY = 'Sure.'
  SILENCE_REPLY = 'Fine. Be that way!'
  STATEMENT_REPLY = 'Whatever.'

  def hey(statement)
    @statement = statement
    answer
  end

  private

  def answer
    answer = REPLIES.find { |reply| send(reply + '?') }
    self.class.class_eval(answer.upcase + '_REPLY')
  end
    
  def shouting?
    @statement.upcase == @statement
  end

  def question?
    @statement.end_with? '?'
  end

  def silence?
    @statement.to_s.strip.empty?
  end

  def statement?
    true
  end

end
