class Bob
  attr_reader :comment

  def hey(comment)
    @comment = comment
    no_comment? ? 'Fine. Be that way.' : reply
  end

  def reply
    can_respond_to_comment? ? scripted_response : 'Whatever.'
  end    

  private

  def scripted_response
    uppercase_comment? ? 'Woah, chill out!' : 'Sure.'
  end

  def can_respond_to_comment?
    uppercase_comment? || question?
  end

  def question?
    comment.end_with?('?')
  end

  def uppercase_comment?
    comment.upcase == comment
  end

  def no_comment?
    comment.nil? || comment.empty?
  end
end
