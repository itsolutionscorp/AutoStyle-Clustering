class Bob
  def hey(string)
    if (string.upcase == string) && (string =~ /[A-Z]/)
      "Woah, chill out!"
      elsif string.strip.empty?
        "Fine. Be that way!"

      elsif string.end_with?("?")
        "Sure."

      else string
        "Whatever."

    end

  end

end
