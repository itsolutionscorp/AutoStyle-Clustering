class Bob
  def hey(input)

    if input.strip == ''
      return "Fine. Be that way!"
    end

    if input.upcase.match(/[A-Z]+/) && input.upcase == input
      return "Woah, chill out!"
    end

    if input[input.length-1] == '?'
      return "Sure."
    end

    "Whatever."
  end
end
