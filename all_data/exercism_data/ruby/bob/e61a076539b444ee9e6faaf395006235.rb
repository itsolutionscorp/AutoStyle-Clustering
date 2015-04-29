class Bob
  def hey(subject)
    if subject==subject.upcase && subject!=subject.downcase
      'Woah, chill out!'
    elsif subject.end_with?('?') 
      'Sure.'
    elsif subject.strip == ''
       'Fine. Be that way!'
    else
      'Whatever.'
    end
  end
end
