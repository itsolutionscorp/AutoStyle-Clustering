class Bob
  def hey(remark)
    if remark.upcase == remark && remark =~ /[A-Za-z]/
      'Whoa, chill out!'
    elsif remark[-1] == '?'
      'Sure.'
    elsif remark =~ /\s{2,}/ || remark == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
