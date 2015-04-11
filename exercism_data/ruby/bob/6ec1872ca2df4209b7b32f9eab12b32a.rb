class Bob
  def hey(input)

    if input.end_with?(".")
      "Whatever."
    elsif input.end_with?("!")
      if input.upcase == input
        "Woah, chill out!"
      else
        "Whatever."
      end
    elsif input.end_with?("?")
      if input.gsub("?", "").to_i != 0
        "Sure."
      elsif input.upcase == input
        "Woah, chill out!"
      else
        "Sure."
      end
    elsif input.end_with?("3")
      'Whatever.'
    elsif input.empty? || input.strip.empty?
      "Fine. Be that way!"
    elsif input.include?("\n")
      "Whatever."
    else
      'Woah, chill out!'
    end

  end
end
