class Bob
  def hey (phrase)
    phrase = phrase.chomp
    if phrase[phrase.size-1] == '?' && phrase[/[a-z0-9]/]       
      return "Sure."
    elsif phrase[/[a-z]/]
      return "Whatever."
    elsif phrase[/[A-Z]/]
      return "Woah, chill out!"
    elsif phrase[/[0-9]/]
      return "Whatever."
    else
      return "Fine. Be that way!"
    end
  end
end
