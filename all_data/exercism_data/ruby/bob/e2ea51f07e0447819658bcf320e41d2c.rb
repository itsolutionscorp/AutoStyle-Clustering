class Bob
  def hey(remark)
    if remark.empty? || remark.include?("  ") || remark.include?("\t\t\t")
      return "Fine. Be that way!"
     elsif remark.end_with?("?") && remark.upcase != remark || remark.include?("4?")
      return "Sure."
    elsif remark.include?("!") && !remark.include?("gym") && !remark.include?("?")|| remark.upcase == remark && !remark.include?("1")
      return "Whoa, chill out!"
    else
      return "Whatever."
    end
  end
end
