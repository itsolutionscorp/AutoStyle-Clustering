class Bob
  def hey(greeting)
    if uppercase?(greeting)
      "Whoa, chill out!"
    elsif greeting.end_with?("?")
      "Sure."
    elsif greeting.empty? || all_whitespace?(greeting)
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end

  private

  def uppercase?(string)
    string =~ /[A-Z]/ && string.upcase == string
  end

  def all_whitespace?(greeting)
    greeting =~ /\A\W+\Z/
  end
end
