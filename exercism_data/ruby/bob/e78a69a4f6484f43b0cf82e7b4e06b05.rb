class Bob
  def initalize
  end

  def hey remark
    case
    when remark.upcase == remark && remark.downcase != remark
      'Whoa, chill out!'
    when remark[-1] == '?'
      'Sure.'
    when remark.gsub(/\s+/, '').empty?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
