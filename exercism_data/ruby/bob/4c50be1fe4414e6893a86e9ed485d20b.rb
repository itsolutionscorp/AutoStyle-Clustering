class Bob

  def hey(remark)
    case remark
    when yell?
      'Whoa, chill out!'
    when question?
      'Sure.'
    when silent?
      'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def yell?
    ->(remark) { 
        isYelling ||= (remark == remark.upcase) && remark =~ /[A-Z]/
      }
  end

  def question?
    ->(remark) {remark =~ /\?\Z/}
  end

  def silent?
    ->(remark) {remark.strip == ""}
  end

end
