class Bob
  def hey(phrase)
    phrase = phrase.scan(/[a-zA-Z0-9\?]*/).join
    if phrase.length == 0
      "Fine. Be that way!"
    elsif phrase[-1] == '?'
      phrase == phrase.upcase || only_num?(phrase) ? "Whoa, chill out!" : "Sure."
    elsif phrase == phrase.upcase && phrase.length != 0 && phrase.scan(/\d/).size != phrase.size
      "Whoa, chill out!"
    else
      "Whatever."
    end
  end

  def only_num?(str)
    str.gsub(/0-9/, "").length == 0
  end

end
