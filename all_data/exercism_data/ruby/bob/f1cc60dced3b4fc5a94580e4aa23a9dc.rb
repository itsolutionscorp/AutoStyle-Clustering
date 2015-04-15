class Bob
  def hey(some_comment)
    if comment_exists(some_comment)
      think_about_comment(some_comment)
    else
      "Fine. Be that way!"
    end
  end
  
  private
  
  def comment_exists(some_comment)
    if some_comment.to_s.strip.empty? == false
      some_comment
    end
  end
  
  def think_about_comment(some_comment)
    if some_comment.upcase == some_comment
      "Woah, chill out!"
    elsif some_comment.end_with? "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
