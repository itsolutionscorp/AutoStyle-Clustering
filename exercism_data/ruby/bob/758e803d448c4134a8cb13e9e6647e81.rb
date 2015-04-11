class Bob

  def hey(remark)
    if remark == remark.upcase  && remark.match(/[A-Z]/)
      'Whoa, chill out!'
    elsif remark[-1,1] == '?'
      'Sure.'
    elsif remark.lstrip == ""
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

end
