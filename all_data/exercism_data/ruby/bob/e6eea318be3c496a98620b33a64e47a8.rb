class Bob
  
  def hey(remark)
    # check if remark is all whitespace
    if remark.gsub(/\s+/, "") == ""
      return "Fine. Be that way!"
    # check and deal with cases where remark contains no alphabetic characters
    elsif remark.gsub(/[a-zA-Z]/, "") == remark
      if remark[-1] == '?'
        return 'Sure.'
      elsif remark[-1] == '!'
        return 'Whoa, chill out!'
      else
        return 'Whatever.'
      end
    # check if remark is all uppercase (yelling)
    elsif remark.upcase == remark
      return 'Whoa, chill out!'
    # check if remark is a question
    elsif remark[-1] == '?'
      return 'Sure.'
    # everything else is a statement
    else
      return 'Whatever.'
    end
  end
end
