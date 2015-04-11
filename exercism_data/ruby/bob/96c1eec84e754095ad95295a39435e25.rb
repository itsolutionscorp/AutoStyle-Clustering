class Bob
  def hey(remark)
    if remark.to_s.empty?
      'Fine. Be that way.'
    elsif remark.upcase == remark
      'Woah, chill out!'
    elsif remark.end_with? '?'
      'Sure.'
    else
      'Whatever.'
    end
  end
end
