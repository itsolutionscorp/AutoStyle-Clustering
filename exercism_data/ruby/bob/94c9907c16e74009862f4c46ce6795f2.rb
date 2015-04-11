class Bob
  def hey(remark)
    case
    when remark.match(/\A\s*\z/)
      'Fine. Be that way!'
    when remark.scan(/[a-z]/).empty? && !remark.scan(/[A-Z]/).empty?
      'Whoa, chill out!'
    when remark.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
