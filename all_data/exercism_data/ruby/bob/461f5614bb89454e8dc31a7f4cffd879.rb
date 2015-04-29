class Bob
  def hey(input)
    case
    when input.strip.empty?
      'Fine. Be that way!'
    when input =~ /[[:alpha:]]/ && input.upcase == input
      "Whoa, chill out!"
    when input.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
