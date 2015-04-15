class Bob
  def hey(remark)
    if remark[-1] == "?" && remark[/[a-z\d]/] 
      "Sure."
    elsif !remark.upcase! && remark[/[A-Za-z]+/]
      "Whoa, chill out!"
    elsif !remark[/\S/]
      "Fine. Be that way!"
    else 
      "Whatever."
    end
  end
end
