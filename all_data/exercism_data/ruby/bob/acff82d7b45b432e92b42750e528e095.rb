class Bob
  def hasCaps? (s)
    s.split(//).any? {
      |c| ('A'..'Z').include?(c)
    }
  end

  def hey (r)
    case
    when r.strip.empty?
      "Fine. Be that way!"
    when r == r.upcase && hasCaps?(r)
      "Woah, chill out!"
    when r[-1, 1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
