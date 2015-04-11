class Bob
  def hey(sentence)
    case
    when sentence.nil?, sentence.empty?
      "Fine. Be that way!"
    when all_upper?(sentence)
      "Woah, chill out!"
    when sentence.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def all_upper?(string)
    string.upcase == string
  end
end
