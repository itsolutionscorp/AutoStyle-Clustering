class Bob
  def hey(str)
    if str == str.upcase
      if str =~ /[A-Z][^a-z]+/
        "Woah, chill out!"
      elsif str.end_with?('?')
        "Sure."
            elsif str.strip == ""
      "Fine. Be that way!"
      else
        "Whatever."
      end
    elsif str.end_with?('?')
      "Sure."
    else
      "Whatever."
    end
  end
end
