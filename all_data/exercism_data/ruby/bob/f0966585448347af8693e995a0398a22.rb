class Bob
  def hey(phrase)
    case
    ## Only empty spaces
    when phrase.gsub(/\s/, "").empty?
      "Fine. Be that way!"
    ## Only numbers
    when phrase.gsub(/[\,\s\!\.]/, "").chars.all? { |i| ("0".."9").cover? i }
      "Whatever."
    ## Question, only numbers (ends with a '?')
    when phrase[-1] == "?" && phrase.gsub(/[^a-zA-Z0-9\s]/, "").chars.all? { |i| ("0".."9").cover?(i) }
      "Sure."
    ## Question, non-caps (ends with a '?')
    when phrase[-1] == "?" && phrase.gsub(/[^a-zA-Z\s]/, "").upcase != phrase.gsub(/[^a-zA-Z\s]/, "")
      "Sure."
    ## Shouting (all alpha characters are upcase)
    when phrase.gsub(/[^a-zA-Z\s]/, "").upcase == phrase.gsub(/[^a-zA-Z\s]/, "")
      "Whoa, chill out!"
    ## Everything else
    else
      "Whatever."
    end
  end
end
