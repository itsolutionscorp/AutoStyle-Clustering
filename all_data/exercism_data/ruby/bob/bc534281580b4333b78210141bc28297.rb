class Bob
  def hey(remark)
    answer = ''
    text = remark.gsub(/[^a-zA-Z\!\?]/,'')
    if(remark.gsub(/\s/,'').empty?)
      answer = "Fine. Be that way!"
    elsif(!text.empty? && text != "?"  && text == text.upcase)
      answer = "Whoa, chill out!"
    elsif(remark[remark.length-1] == "?")
      answer = "Sure."
    else
      answer = "Whatever."
    end
    return answer
  end
end
