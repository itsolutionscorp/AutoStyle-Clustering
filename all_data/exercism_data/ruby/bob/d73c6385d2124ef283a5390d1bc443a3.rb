class Bob

  def hey(remark)
    if remark.upcase == remark && remark.downcase != remark
      'Whoa, chill out!'
    elsif remark[-1] == '?'
      'Sure.'
    elsif remark.strip.empty?
      'Fine. Be that way!'
    else
      remark ="Whatever."
    end
  end

end
