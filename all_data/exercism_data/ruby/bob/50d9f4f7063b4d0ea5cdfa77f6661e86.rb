class Bob
  def hey(comment)
    @comment = comment || ''
    case
    when no_comment?
      'Fine. Be that way.' 
    when shouted?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def no_comment?
    @comment.empty?
  end

  def question?
    @comment.end_with?('?')
  end

  def shouted?
    @comment.upcase == @comment
  end
end
