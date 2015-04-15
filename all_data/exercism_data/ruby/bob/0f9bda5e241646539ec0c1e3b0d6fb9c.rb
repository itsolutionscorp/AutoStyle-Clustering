class Bob
  ANSWERS = { question: 'Sure.',
              yell: 'Whoa, chill out!',
              nothing: 'Fine. Be that way!',
              anything: 'Whatever.'}

  def hey remark
    case
    when word_upercase?(remark)
      ANSWERS[:yell]
    when remark[/.\z/] == '?'
      ANSWERS[:question]
    when remark.lstrip.empty?
      ANSWERS[:nothing]
    else
      ANSWERS[:anything]
    end
  end

  def word_upercase? remark
    remark.scan(/\S\w\D/).empty? ? false : remark.upcase == remark
  end
end
