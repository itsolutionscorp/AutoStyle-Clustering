class Bob
  def hey(phrase)
    case
    when phrase == "" || phrase.nil?
      answer = "Fine. Be that way."
    when phrase.upcase == phrase
      answer = "Woah, chill out!"
    when phrase[-1] == "?"
      answer = "Sure."
    else
      answer = "Whatever."
    end

    answer
  end
end
