class Bob
  def hey(remark)
  answers=["Sure.","Whoa, chill out!","Fine. Be that way!","Whatever."]
  answer=answers[2]
  if remark=~/\S/ then answer=answers[3] end
  if remark.end_with?("?") then answer=answers[0] end
  if (remark.upcase==remark)&(remark.count("a-zA-Z")>0) then answer=answers[1] end
  
  answer
  end

end
