class Bob
  def hey(phrase)
    case
    when silence?(phrase)
      "Fine. Be that way!"
    when yelling?(phrase)
      "Whoa, chill out!"
    when question?(phrase)
      "Sure."
    else
      "Whatever."
    end
  end

  private

  def question?(phrase)
    phrase[-1] == "?"
  end

  def yelling?(phrase)
    phrase.upcase == phrase && phrase.gsub(/[^A-Z]/, "").length > 0
  end

  def silence?(phrase)
    phrase.gsub(/\s/, "").empty?
  end

  def only_numbers?(phrase)
    phrase.gsub(/[^a-zA-Z0-9]/, "").chars.all? { |i| ("0".."9").cover?(i) }
  end
end
