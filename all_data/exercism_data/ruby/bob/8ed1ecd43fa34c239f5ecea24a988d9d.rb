class Bob
  def hey line
    if line.gsub(/\s/, "").size == 0 then "Fine. Be that way!"
    elsif not /[a-z]/ =~ line and /[A-Z]/ =~ line then "Whoa, chill out!"
    elsif line.chars.last == "?" then "Sure."
    else "Whatever."
    end
  end
end
