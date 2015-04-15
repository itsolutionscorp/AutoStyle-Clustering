class Bob
  def hey lecture

    if lecture == nil or lecture.strip == ""
      "Fine. Be that way!"

    elsif lecture.upcase == lecture
      "Woah, chill out!" 

    elsif lecture.end_with?("?")
      "Sure."

    else
      "Whatever."
    end
  end
end
