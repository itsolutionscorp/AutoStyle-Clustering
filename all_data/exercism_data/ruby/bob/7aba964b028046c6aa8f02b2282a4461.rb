class Bob
  def hey(question)
    case question
    when %r{[A-Z]+(!|$)}
      "Woah, chill out!"
    when %r{.\?$}
      "Sure."
    when ""
      "Fine. Be that way."
    else
      "Whatever."
    end
  end
end
