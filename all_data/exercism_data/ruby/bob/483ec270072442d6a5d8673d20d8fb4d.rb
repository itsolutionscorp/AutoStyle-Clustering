class Bob
  ADDRESSED_WITHOUT_SAYING_ANYTHING = /\A *\z/
  ASKED_A_QUESTION = /\?\z/
  YELLED_AT = /\A[^[:lower:]]*[[:upper:]]+[^[:lower:]]*\z/
  
  def hey (text)
    case text
      when ADDRESSED_WITHOUT_SAYING_ANYTHING
        "Fine. Be that way!"
      when YELLED_AT
        "Woah, chill out!"
      when ASKED_A_QUESTION
        "Sure."
      else
        "Whatever."
    end
  end

end
