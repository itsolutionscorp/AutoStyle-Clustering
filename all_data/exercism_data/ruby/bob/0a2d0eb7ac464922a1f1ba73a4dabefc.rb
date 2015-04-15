class Bob
  def hey(sentence)
    if sentence.nil? or sentence.strip.empty?
      return "Fine. Be that way!"
    elsif sentence == sentence.upcase
      return "Woah, chill out!"
    elsif sentence[-1] == "." or sentence[-1] == "!"
      return "Whatever."
    elsif sentence[-1] == "?"
      return "Sure."
    end
  end
end
