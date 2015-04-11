class Bob

  def shouting? some_words
    some_words.match(/^[A-Z0-9!@#$%&\^()*, ]+[!\?]$/) or some_words.match(/^[A-Z0-9, ]+!$/) or some_words.match(/^[A-Z, ]+$/)
  end
  
  def just_numbers? some_words
    some_words.match(/^[0-9, ]\??$/)
  end

  def question? some_words
    some_words.match(/.+\?$/)
  end

  def silence? some_words
    some_words.match(/^[\W]*$/)
    #some_words.match(/^[ ]*$/)
  end

  def hey some_words
    some_words.gsub!("\n", "")
    if shouting? some_words and not (just_numbers? some_words or silence? some_words)
      "Woah, chill out!"
    elsif question? some_words
       "Sure."  
    elsif silence? some_words
      "Fine. Be that way!"
    else             
      "Whatever."
    end
  end

end
