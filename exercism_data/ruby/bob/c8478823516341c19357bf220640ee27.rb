class Bob

  def hey remark
    
    if making_a_big_deal remark
      'Woah, chill out!'
    elsif dumb_question remark
      'Sure.'
    elsif cold_shoulder remark
      'Fine. Be that way!'
    else
      'Whatever.'
    end

  end

  private

  # allcaps check HUZZAH!
  def making_a_big_deal remark
    remark.upcase == remark && remark.match(/[A-Z]/)
  end

  def dumb_question remark
    remark.end_with? '?'
  end

  # strip to include white space
  def cold_shoulder remark
    '' == remark.strip
  end
end
