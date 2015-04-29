class String
  def exists
    strip.empty? == false
  end
  
  def is_yelling
    upcase == self
  end
  
  def is_a_question
    end_with? "?"
  end
end

class Bob  
  def hey (the_comment)
    think_about (the_comment.to_s)
  end
  
  private
  
  def think_about (the_comment)
    if the_comment.exists
      react_to (the_comment)
    else
      "Fine. Be that way!"
    end
  end
  
  def react_to (the_comment)
    if the_comment.is_yelling
      "Woah, chill out!"
    elsif the_comment.is_a_question
      "Sure."
    else
      "Whatever."
    end
  end
end
