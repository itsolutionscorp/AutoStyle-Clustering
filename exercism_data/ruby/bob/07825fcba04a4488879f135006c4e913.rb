class Bob


def hey(arg)
  if (arg.upcase == arg) && (arg =~ /[A-Z]/)
  "Woah, chill out!"
  elsif arg.strip.empty?
    "Fine. Be that way!"

  elsif arg.end_with?("?")
      "Sure."
  else
      "Whatever."
  end
end
end
