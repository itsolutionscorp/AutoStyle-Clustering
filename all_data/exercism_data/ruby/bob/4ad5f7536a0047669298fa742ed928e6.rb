class Bob
  def hey(input)

    # If input is whitespace only, he's being a jerk for saying nothing
    if input.strip === "" then
      return "Fine. Be that way!"
    end

    # If input is equal to .toUpperCase-d input, he's yelling at me
    if(input === input.upcase) then
      return "Woah, chill out!"
    end

    # If input ends with a question mark, I'm ok with his question
    if(input[-1] === "?") then
      return "Sure."
    end

    # Otherwise
    return "Whatever."

  end
end
