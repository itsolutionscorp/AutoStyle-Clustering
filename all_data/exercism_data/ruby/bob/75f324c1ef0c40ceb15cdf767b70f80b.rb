class Bob
  def hey(phrase)
    if phrase.strip.empty?
      "Fine. Be that way!"
    elsif phrase =~ /[A-Z]+/ && phrase.upcase == phrase
      # Phrase must contain at least one letter and be all upcase
      #
      # Gotcha, this does not work for German language
      # a gem like unicode_utils is needed
      "Woah, chill out!"
    elsif phrase.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
