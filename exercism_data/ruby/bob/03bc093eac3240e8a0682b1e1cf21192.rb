class Bob
  def hey(string)
    case
    when nothing?(string)  then "Fine. Be that way!"
    when screamed?(string) then "Woah, chill out!"
    when question?(string) then "Sure."
    else
      "Whatever."
    end
  end

  def nothing?(string)
    string.strip == ''
  end

  def screamed?(string)
    string == string.upcase && string != string.swapcase
  end

  def question?(string)
    string[-1] == "?"
  end
end
