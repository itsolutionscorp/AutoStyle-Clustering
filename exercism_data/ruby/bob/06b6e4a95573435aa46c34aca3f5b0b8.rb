class Bob

  def hey(str)
    if "#{str}".empty?
      "Fine. Be that way."
    elsif str.end_with? "?"
      "Sure."
    elsif str == str.upcase
      "Woah, chill out!"
    else
      "Whatever."
    end
  end

end
