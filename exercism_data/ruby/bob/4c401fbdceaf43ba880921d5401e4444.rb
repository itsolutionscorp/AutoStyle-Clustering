class Bob
  def hey remark
    if remark =~ /\A[^[:lower:]]*\Z/ and /[[:alpha:]]/.match(remark)
      "Whoa, chill out!"
    elsif remark.chars.last == "?"
      "Sure."
    elsif remark.strip.empty?
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
