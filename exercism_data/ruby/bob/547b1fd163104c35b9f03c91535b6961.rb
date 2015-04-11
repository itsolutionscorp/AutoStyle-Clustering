class Bob
  Addressed_Without_Saying_Anything = /\A *\z/
  Asked_a_Question = /\?\z/
  Yelled_At = /\A[^a-z]*[A-Z]+[^a-z]*\z/
  
  def hey text
    case text
    when Addressed_Without_Saying_Anything
      "Fine. Be that way!"
    when Yelled_At
      "Woah, chill out!"
    when Asked_a_Question
      "Sure."
    else
      "Whatever."
    end
  end

end
