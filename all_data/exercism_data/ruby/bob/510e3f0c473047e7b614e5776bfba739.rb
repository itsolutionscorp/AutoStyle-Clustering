# this is bob
#
class Bob
  def hey(phrase)
    if phrase.strip.length == 0
      "Fine. Be that way!"
    elsif phrase == phrase.upcase && phrase =~ /[a-z]/i
      "Woah, chill out!"
    elsif phrase[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
