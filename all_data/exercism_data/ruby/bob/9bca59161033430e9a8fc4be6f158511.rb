class Bob

  class Inquiry < String
    def question?
      self.end_with?('?')
    end

    def yelled?
      self.upcase == self
    end

    def meaningless?
      self.empty?
    end
  end

  def hey(message)
    inquiry = Inquiry.new(message.to_s)

    if    inquiry.meaningless? then 'Fine. Be that way.'
    elsif inquiry.yelled?      then 'Woah, chill out!'
    elsif inquiry.question?    then 'Sure.'
    else                            'Whatever.'
    end
  end

end
