class Bob
  def hey(remark = '')
    case
    when remark.match(/[a-zA-Z]/) && remark.upcase == remark
      'Whoa, chill out!'
    when remark.end_with?('?')
      'Sure.'
    when remark.strip == ''
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
