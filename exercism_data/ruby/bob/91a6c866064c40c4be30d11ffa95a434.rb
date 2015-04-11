class Bob
  def hey(input)
    case
    when input.strip.empty?
      'Fine. Be that way!'
    when input =~ /[a-z]/i && input.upcase == input
      "Woah, chill out!"
    when input.ends_with? '?'
      "Sure."
    else
      "Whatever."
    end
  end
end
