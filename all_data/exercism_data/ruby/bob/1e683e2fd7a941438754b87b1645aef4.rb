# Seems like cost(refactoring_now) >= cost(refactoring_later)

class Bob
  def hey(what_was_said)
    comment = Comment.new(what_was_said)
    if comment.snubbed?
      'Fine. Be that way.'
    elsif comment.inquired?
      'Sure.'
    elsif comment.shouted?
      'Woah, chill out!'
    else
      'Whatever.'
    end
  end
end

class Comment
  attr_reader :comment
  def initialize(comment)
    @comment = comment || ""
  end

  def snubbed?
    comment.empty?
  end

  def inquired?
    comment.end_with?('?')
  end

  def shouted?
    comment.upcase == comment
  end  
end
