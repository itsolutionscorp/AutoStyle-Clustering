class Bob
  def hey(phrase)
    if only_silence(phrase)
      return 'Fine. Be that way!'
    elsif ends_in_exclaimation(phrase) && !contains_lower_case(phrase)
        return "Whoa, chill out!"
    elsif !contains_lower_case(phrase) && !contains_numbers(phrase)
      return "Whoa, chill out!"
    elsif ends_in_question_mark(phrase)
      return 'Sure.'
    else
      return "Whatever."
    end
  end
  
  def ends_in_exclaimation(phrase)
    if phrase[-1] == '!'
      return true
    end
  end

  def ends_in_question_mark(phrase)
    if phrase[-1] == '?'
      return true
    end
  end

  def contains_lower_case(phrase)
    if phrase.match /[a-z]+/
      return true
    end
  end

  def contains_numbers(phrase)
    if phrase.match /[0-9]+/
      return true
    end
  end

  def only_silence(phrase)
    if (phrase == '') || (phrase == phrase.match(/[\s]+/).to_s )
      return true
    end
  end

end
