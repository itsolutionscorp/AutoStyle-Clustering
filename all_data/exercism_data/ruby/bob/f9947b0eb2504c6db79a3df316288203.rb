class Bob
  def hey remark
    remark.strip!
    if remark.length == 0
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
