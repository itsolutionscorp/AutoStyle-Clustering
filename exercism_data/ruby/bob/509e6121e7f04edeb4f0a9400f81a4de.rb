class Bob
  attr_reader :comment

  def hey(comment)
    @comment = comment
    no_comment? ? 'Fine. Be that way.' : reply
  end

  def reply
    return 'Whatever.' unless can_respond_to_comment?
    scripted_responses
  end    

  private

  def scripted_responses
    if uppercase_comment?
      'Woah, chill out!'
    elsif question?
      'Sure.'
    end
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
