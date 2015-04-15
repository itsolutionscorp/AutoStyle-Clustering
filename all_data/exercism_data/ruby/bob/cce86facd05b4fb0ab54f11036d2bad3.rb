class Bob
  def hey(remark)
    if remark.strip.empty?
      'Fine. Be that way!'
    elsif remark == remark.upcase && letter_included?(remark)
      'Whoa, chill out!'
    elsif remark.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  def letter_included?(remark)
    !remark.gsub(/[^a-z]/i, '').empty?
  end
end
