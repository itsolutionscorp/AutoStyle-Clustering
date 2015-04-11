class Bob
  
  def hey comment
    comment = Comment.new(comment)
    return "Fine. Be that way!" if comment.silent?
    return "Woah, chill out!" if comment.shouting?
    return "Sure." if comment.question?
    "Whatever."
  end
  
end

private

class Comment
  
  def initialize comment
    if comment.is_a? String
      @comment = comment
    else
      raise TypeError, "wrong argument type #{comment.class} (expected String)"
    end
  end
  
  def silent?
    @comment.strip.empty?
  end
  
  def shouting?
    @comment == @comment.upcase
  end
  
  def question?
    @comment.end_with?('?')
  end
  
end
