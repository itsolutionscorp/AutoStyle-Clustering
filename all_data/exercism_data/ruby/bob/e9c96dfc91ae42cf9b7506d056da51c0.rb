class Bob
  def hey(inquire)
    if inquire == inquire.upcase && inquire.strip != ""
        "Woah, chill out!"
      elsif inquire.end_with?("?")
      "Sure."
      elsif !inquire.empty? && inquire.strip != ""
        "Whatever."
      elsif inquire.strip == ""
        "Fine. Be that way!"
      end
    end
end
