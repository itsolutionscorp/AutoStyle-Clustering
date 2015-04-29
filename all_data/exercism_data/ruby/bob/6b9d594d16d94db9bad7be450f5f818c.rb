class Bob
  def initialize
  end

  def hey(teenager)
    if (teenager.upcase == teenager) && (teenager =~/[A-Z]/)
      "Woah, chill out!"
    elsif teenager.end_with? "?"
      "Sure."
    elsif teenager.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
