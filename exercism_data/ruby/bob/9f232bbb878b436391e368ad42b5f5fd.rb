class Bob
  def hey(input)

    # If input is whitespace only, he's being a jerk for saying nothing
    if input.strip == "" then
      "Fine. Be that way!"

    # If input is equal to .toUpperCase-d input, he's yelling at me
    elsif(input == input.upcase) then
      "Woah, chill out!"

    # If input ends with a question mark, I'm ok with his question
    # for the sake of kindness, we'll trim any post-question-mark whitespace
    elsif(input.strip[-1] == "?") then
      "Sure."

    # Otherwise
    else
      "Whatever."
    end

  end
end
