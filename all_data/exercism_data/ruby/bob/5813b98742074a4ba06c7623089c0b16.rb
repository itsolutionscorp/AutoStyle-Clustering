class Bob

  def hey(remark)
    if remark == remark.upcase && !remark.scan(/[a-zA-Z]/).empty?
      return "Whoa, chill out!"
    elsif remark[-1] == "?"
      return "Sure."
    elsif remark.scan(/\w/).empty?
      return "Fine. Be that way!"
    else
      return "Whatever."
    end
      
  end


end
