class Bob
  def hey(comment)
    @comment = comment
    reply
  end

  private

  def reply
    case
    when cannot_respond
      'Fine. Be that way.' 
    when shouted?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end    

  def cannot_respond
    @comment.nil? || @comment.empty?
  end

  def question?
    @comment.end_with?('?')
  end

  def shouted?
    @comment.upcase == @comment
  end
end
