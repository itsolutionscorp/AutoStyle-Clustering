class ::Bob
  def hey(str)
    if str.empty? || str.strip.empty?
      "Fine. Be that way!"
    elsif str.upcase == str
      "Woah, chill out!"
    elsif str.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
