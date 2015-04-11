class Bob
  def hey(word)
    if word.nil? || word.empty?
      "Fine. Be that way."
    elsif word == word.upcase
      "Woah, chill out!"
    elsif word.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end
end
