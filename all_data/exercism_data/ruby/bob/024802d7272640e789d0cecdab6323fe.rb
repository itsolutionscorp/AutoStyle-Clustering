class Bob
  WHITESPACE = /\A *\Z/
  ALL_UPPERCASE = /\A[^a-z]*\Z/
  ENDS_WITH_QUESTION_MARK = /\?\Z/
  
  def hey(text)
    
    case text
      when WHITESPACE then "Fine. Be that way!"
      when ALL_UPPERCASE then 'Woah, chill out!'
      when ENDS_WITH_QUESTION_MARK then 'Sure.'
      else "Whatever."
    end
  end
end
