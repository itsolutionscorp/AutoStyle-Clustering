class Bob
  def hey(remark)
    if (remark =~ /[A-Z]{4}/) != nil || (remark =~ /[A-Z]{2}+!/) != nil
    "Whoa, chill out!"
    elsif remark.end_with?("?")
      "Sure."
    elsif remark.strip.empty?  
      "Fine. Be that way!"
    else
      "Whatever."
    end
  end
end
