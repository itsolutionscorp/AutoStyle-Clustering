class Bob
  def hasCaps? (string)
    string.split("").any? {
      |char| ('A'..'Z').include?(char)
    }
  end

  def hey (saying)
    case
    when saying.strip.empty?
      "Fine. Be that way!"
    when saying == saying.upcase && hasCaps?(saying)
      "Woah, chill out!"
    when saying[-1, 1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
