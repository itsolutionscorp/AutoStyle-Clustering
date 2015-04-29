class Bob
  def hey(remark)
    if remark.strip.empty? == true
    "Fine. Be that way!"
  elsif remark == remark.upcase && remark.downcase != remark
      "Whoa, chill out!"
    elsif remark.end_with?('?') == true
      "Sure."
    else
      "Whatever."
    end
  end
end
