class Bob
  def hey(message)
    if ends_with_question_mark?(message) && (contains_undercase?(message) || contains_numbers?(message))
      "Sure."
    elsif ends_with_exclamation?(message) && contains_numbers?(message)
      "Whoa, chill out!"
    elsif contains_undercase?(message) || contains_numbers?(message)
      "Whatever."
    elsif silent?(message)
      "Fine. Be that way!"
    else
      "Whoa, chill out!"
    end
  end

  def contains_undercase?(str)
      (str.count ('a'..'z').to_a.join) > 0
  end

  def contains_numbers?(str)
    (str.count (0..9).to_a.join) > 0
  end

  def ends_with_question_mark?(str)
    str[-1] == '?'
  end
  def ends_with_exclamation?(str)
    str[-1] == '!'
  end
  def silent?(str)
    str.strip.length == 0
  end
end
