class Bob

  def hey(phrase)

    # if the phrase is empty
    if phrase.gsub(/\s+/, " ").strip.empty?
      "Fine. Be that way!"
    # if the phrase is 'yelling'
    elsif phrase.upcase === phrase
      "Woah, chill out!"
    # if the phrase is a question
    elsif phrase[-1] === "?"
      "Sure."
    # otherwise whatever if phrase exists
    else
      "Whatever." if phrase
    end

  end

end
