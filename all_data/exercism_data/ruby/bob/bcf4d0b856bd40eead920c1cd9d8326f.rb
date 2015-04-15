class Bob
  def hey(remark)
    respond_to(remark)
  end

  private
  def respond_to(remark)
    type = type_of(remark)
    { question: 'Sure.',
      yelling:  'Woah, chill out!',
      silence:  'Fine. Be that way!',
      other:    'Whatever.'
    }[type]
  end

  def type_of(remark)
    if    all_whitespace?(remark) then :silence
    elsif all_uppercase?(remark)  then :yelling
    elsif remark.end_with?('?')   then :question
    else                               :other
    end
  end

  def all_whitespace?(remark)
    remark.strip.empty?
  end

  def all_uppercase?(remark)
    remark == remark.upcase
  end
end
