class Bob
  def hey something
    if something == something.upcase && has_letters?(something)
      "Woah, chill out!"
    elsif something.end_with?('?')
      "Sure."
    elsif something.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  def has_letters? string
   string.count("a-zA-Z") > 0
  end
end
