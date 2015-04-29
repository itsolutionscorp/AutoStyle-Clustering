class Bob
  def hey(str)
    if str.strip == "" || str.nil?
      "Fine. Be that way!"
    elsif str.upcase == str
      "Woah, chill out!"
    elsif str[-1] == "?"
      "Sure."
    else
      "Whatever."
    end
  end
end
