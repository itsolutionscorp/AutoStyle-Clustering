class Bob

  def hey(arg)

    if (arg == arg.upcase) && (arg =~ /[A-Z]/)
      "Woah, chill out!"
    elsif arg.end_with? "?"
      "Sure."
    elsif arg.upcase != arg.downcase
      "Whatever."
    elsif arg.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end

  end
end
