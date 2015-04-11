class Bob
  def hey(arg)

    alphabets = arg.gsub(/\W|\d/, "")
    numbers = arg.gsub(/\D/, "")
    special_characters = arg.gsub(/\w|\d|\s/, "")

    if alphabets.empty? && numbers.empty? && special_characters.empty?
      return "Fine. Be that way!"
    end

    if alphabets.upcase == alphabets && !alphabets.empty?
      return "Woah, chill out!"
    end

    if arg[-1] == "?"
      return "Sure."
    end

    return "Whatever."
  end
end
