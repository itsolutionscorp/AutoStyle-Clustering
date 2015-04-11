class Bob
  ANSWERS = { question: 'Sure.',
              yell: 'Whoa, chill out!',
              nothing: 'Fine. Be that way!',
              anything: 'Whatever.'}

  def hey remark
    case
    when word_upcase?(remark)
      ANSWERS[:yell]
    when remark.end_with?('?')
      ANSWERS[:question]
    when remark.lstrip.empty?
      ANSWERS[:nothing]
    else
      ANSWERS[:anything]
    end
  end

  def word_upcase? remark
    characters?(remark) && remark.upcase == remark ? true : false
  end

  def characters? remark
    !remark.scan(/\S\w\D/).empty?
  end
end
