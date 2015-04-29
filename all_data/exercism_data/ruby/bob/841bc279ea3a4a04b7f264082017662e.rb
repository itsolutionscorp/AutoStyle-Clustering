class Bob
  def hey(str)
    if str.nil? || str.empty?
      "Fine. Be that way."
    elsif str =~ /\?\Z/
      "Sure."
    elsif str.upcase == str
      "Woah, chill out!"
    else
      "Whatever."
    end
  end
end
