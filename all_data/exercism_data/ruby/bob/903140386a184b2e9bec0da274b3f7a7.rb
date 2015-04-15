class Bob
  def hey(remark)
    respond_to(type_of(remark))
  end

  private
  def type_of(remark)
    if    all_whitespace?(remark)        then :silence
    elsif all_uppercase?(remark)         then :yelling
    elsif ends_in_question_mark?(remark) then :question
    else                                      :other
    end
  end

  def respond_to(type)
    { question: 'Sure.',
      yelling:  'Woah, chill out!',
      silence:  'Fine. Be that way!',
      other:    'Whatever.'
    }[type]
  end

  def all_whitespace?(remark)
    remark.strip.empty?
  end

  def all_uppercase?(remark)
    remark == remark.upcase
  end

  def ends_in_question_mark?(remark)
    remark =~ /\?$/
  end
end
