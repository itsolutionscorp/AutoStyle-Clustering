class Bob
  def hey(str)
    if str.nil? || str.empty?
      "Fine. Be that way!"
    elsif str.upcase == str
      "Woah, chill out!"
    elsif str.match /\?\Z/
      "Sure."
    else
      "Whatever."
    end
  end
end
