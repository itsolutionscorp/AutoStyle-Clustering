class Bob
  
  def hey(string)
    if string.scan(/\w/).empty?
      return 'Fine. Be that way!'
    elsif string.gsub(/[1..9]/, "") == string.gsub(/[1..9]/, "").upcase && !string.gsub(/[\d\W]/, "").empty?
      return 'Whoa, chill out!'
    elsif string[-1] == "?"
      return 'Sure.'
    else
      return 'Whatever.'
    end
  end
end
