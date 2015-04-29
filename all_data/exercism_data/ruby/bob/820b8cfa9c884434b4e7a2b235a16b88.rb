class Bob
  def hey(remark)
    if remark.strip.empty?
      'Fine. Be that way!'
    elsif remark.upcase == remark && remark.downcase != remark
      'Whoa, chill out!'
    elsif remark[-1] == '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
