class Bob
  def hey(remark)
    p remark
    if remark =~ upcase && !(remark =~ downcase) && !(remark =~ numbers)
      'Whoa, chill out!'
    elsif remark =~ ends_with_questionmark
      'Sure.'
    elsif (remark =~ numbers) && (remark =~ upcase) && !(remark =~ downcase)
      'Whoa, chill out!'
    elsif (remark =~ starts_with_spaces) || (remark =~ empty)  && !(remark =~ numbers) && !(remark =~ downcase) && !(remark =~ upcase)
      'Fine. Be that way!'
    else
      'Whatever.'
    end 
  end
  def numbers
    /[\d]+/
  end
  def downcase
    /[a-z]+/
  end
  def upcase
    /[A-Z]+/
  end
  def ends_with_questionmark
    /[\?]$/
  end
  def starts_with_spaces
    /^[\s]+/
  end
  def empty
    /^$/
  end
end
