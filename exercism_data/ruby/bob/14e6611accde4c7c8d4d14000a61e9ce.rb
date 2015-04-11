class Bob
  
  def hey comment
    comment = Comment.new(comment)
    return "Fine. Be that way!" if comment.is_silence?
    return "Woah, chill out!" if comment.is_shout?
    return "Sure." if comment.is_question?
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
  
  def is_silence?
    @comment.strip.empty?
  end
  
  def is_shout?
    @comment == @comment.upcase
  end
  
  def is_question?
    @comment.end_with?('?')
  end
  
end
