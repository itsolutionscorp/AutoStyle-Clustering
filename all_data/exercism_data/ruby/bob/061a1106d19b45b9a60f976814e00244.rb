class Bob
  def init
  end

  def hey(string)
    response = "Whatever."
    response = "Sure." if string[-1] == "?"
    response = "Woah, chill out!" if string == string.upcase and string != string.downcase
    response = "Fine. Be that way!" if string.gsub(" ","").gsub("\t","").empty?
    response
  end
end
