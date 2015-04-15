class Bob
  def hey val
    if val.match /\S+/
      if is_yell? val
        "Woah, chill out!"
      elsif is_question? val
        "Sure."
      else
        "Whatever."
      end
    else
      "Fine. Be that way!"
    end
  end

  def is_yell? string
    string == string.upcase && string.match(/[A-Z]+/)
  end

  def is_question? string
    string[-1] == '?'
  end
end
