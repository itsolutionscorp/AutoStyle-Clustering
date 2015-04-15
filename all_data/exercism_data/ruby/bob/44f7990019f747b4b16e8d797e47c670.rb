class Bob
  def hey(remark)
    remark = remark.gsub(/[^0-9a-z!?]/i, ' ')

    if remark.strip == ''
      'Fine. Be that way!'
    elsif /(.)*[A-Za-z]+(.)*/.match(remark)
      if remark[-1, 1] == '?' && remark != remark.upcase
        'Sure.'
      elsif remark == remark.upcase
        'Whoa, chill out!'
      else
        'Whatever.'
      end
    else
      if remark[-1, 1] == '?'
        'Sure.'
      else
        'Whatever.'
      end
    end
  end
end
