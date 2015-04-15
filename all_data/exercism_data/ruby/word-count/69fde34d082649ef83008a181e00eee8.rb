class Bob
  
  def hey comment
    comment = Comment.new(comment)
    return "Fine. Be that way!" if comment.silence?
    return "Woah, chill out!" if comment.shout?
    return "Sure." if comment.question?
    "Whatever."
  end
  
end

class Comment
  
  def initialize comment
    @comment = comment.to_s
  end
  
  def silence?
    @comment.strip.empty?
  end
  
  def shout?
    @comment == @comment.upcase
  end
  
  def question?
    @comment.end_with?('?')
  end
  
end
