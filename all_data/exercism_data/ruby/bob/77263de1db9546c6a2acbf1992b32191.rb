class Bob
  def hey(arg)
    if arg == arg.upcase && (arg =~ /[A-Z]/)
    "Woah, chill out!"
    elsif arg.include?("?")
      "Sure."
    elsif arg.downcase != arg.upcase
      "Whatever."
    else
      "Whatever."
    end
  end
end
