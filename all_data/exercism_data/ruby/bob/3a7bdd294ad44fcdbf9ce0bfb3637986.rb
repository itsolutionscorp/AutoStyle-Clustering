class Bob
  @@response = {default: 'Whatever.',
                fine: 'Fine. Be that way!',
                sure: 'Sure.',
                whoa: 'Whoa, chill out!',}

  def hey(remark)
    remark = Remark.new(remark)
    if remark.upcase?
      @@response[:whoa]
    elsif remark.ends_with_question_mark?
      @@response[:sure]
    elsif remark.blank? or remark.empty?
      @@response[:fine]
    else
      @@response[:default]
    end
  end

end

class Remark < String
  def upcase?
    self == self.upcase and self =~ /[a-zA-Z]/
  end

  def ends_with_question_mark?
    self[-1] == '?'
  end

  def blank?
    self.strip =~ /^\s*$/
  end
end
