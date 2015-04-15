class Bob
  def hey(text)
    case
    when text.strip == ""
      "Fine. Be that way!"
    when text == text.upcase
      "Woah, chill out!"
    when text[-1] == '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
