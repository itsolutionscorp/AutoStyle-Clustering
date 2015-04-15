class Bob


  def hey(say)

    if (say.upcase == say) && (say.downcase != say.upcase)
      'Woah, chill out!'
    elsif say[-1] == "?"
      'Sure.'
    elsif say.strip == ""
      'Fine. Be that way!'
    else
      "Whatever."
    end
  end

end
