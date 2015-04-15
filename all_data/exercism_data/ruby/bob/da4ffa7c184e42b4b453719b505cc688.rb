class Bob
  def hey(something)
    if something.nil? || something.empty?
      "Fine. Be that way!"
    elsif something.upcase == something
      "Woah, chill out!"
    elsif something.end_with? '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
