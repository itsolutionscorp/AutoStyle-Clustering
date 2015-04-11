class Bob
  attr_reader :comment

  def hey(comment)
    return 'Fine. Be that way.' if comment.nil? || comment.empty?
    @comment = comment
    reply
  end

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

  private

  def can_respond_to_comment?
    shouted? || question?
  end

  def question?
    comment.end_with?('?')
  end

  def shouted?
    comment.upcase == comment
  end
end
