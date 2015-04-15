# Seems like cost(refactoring_now) >= cost(refactoring_later)

class Bob
  def hey(what_was_said)
    comment = Comment.new(what_was_said)
    if comment.silence?
      'Fine. Be that way.'
    elsif comment.question?
      'Sure.'
    elsif comment.was_shouted?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class Comment
  attr_reader :comment
  def initialize(comment)
    @comment = comment
  end

  def silence?
    [nil, ""].include? comment
  end

  def question?
    comment and comment.end_with?('?')
  end

  def was_shouted?
    comment and comment.upcase == comment
  end  
end
