class Bob
  def initialize
  end

  def hey text
    if text.strip.empty?
      "Fine. Be that way!"
    elsif text.each_char.map { |c| letter?(c) }.any? && text.each_char.map { |c| letter?(c) && c.capitalize == c || !letter?(c) }.all?
      "Woah, chill out!"
    elsif text.end_with?("?")
      "Sure."
    else
      "Whatever."
    end
  end

  LETTER_RANGE = ("A".."Z").to_a
  def letter? c
    LETTER_RANGE.include?(c.capitalize)
  end
end
