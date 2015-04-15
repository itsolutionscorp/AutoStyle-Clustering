class Bob
  def hey(sen)
    return "Fine. Be that way!" if sen.strip == ""
    return "Whoa, chill out!" if sen == sen.upcase && sen != sen.downcase
    return "Sure." if sen[-1] == "?"
    "Whatever."
  end
end
