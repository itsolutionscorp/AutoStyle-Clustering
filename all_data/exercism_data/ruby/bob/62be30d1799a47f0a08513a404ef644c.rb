class Bob
  def hey(str)
    get_response str.strip
  end

  def get_response(str)
    if str == str.upcase && str =~ /[A-Z]+/
      "Woah, chill out!"
    elsif str.end_with?('?')
      "Sure."
    elsif str == ""
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
