class Bob
  def hey(comment)
    return 'Fine. Be that way.' if comment.nil? || comment.empty?
    @comment = comment
    reply
  end

  private

  def reply
    case 
    when shouted?
      'Woah, chill out!'
    when question?
      'Sure.'
    else
      'Whatever.'
    end
  end    

  def question?
    @comment.end_with?('?')
  end

  def shouted?
    @comment.upcase == @comment
  end
end
