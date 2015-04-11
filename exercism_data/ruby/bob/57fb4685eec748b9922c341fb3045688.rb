class Bob
  def hey(something)
    if !something || something.length == 0
      "Fine. Be that way."
    elsif something == something.upcase
      "Woah, chill out!"
    elsif something =~ /\?$/
      "Sure."
    else
      "Whatever."
    end
  end
end
