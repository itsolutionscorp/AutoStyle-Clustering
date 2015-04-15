class Bob
  def hey(sentence)
    if sentence.strip == ""
      "Fine. Be that way!"
    elsif sentence.upcase == sentence && sentence.downcase != sentence
      "Whoa, chill out!"
    elsif sentence[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
