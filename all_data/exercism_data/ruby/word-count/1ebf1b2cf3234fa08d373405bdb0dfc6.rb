class Bob

  def hey(inquiry)
    @inquiry = inquiry

    if ignored?
      'Fine. Be that way!'
    elsif yelled?
      'Woah, chill out!'
    elsif questioned?
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def questioned?
    @inquiry.end_with?("?")
  end

  def yelled?
    @inquiry  == @inquiry.upcase
  end

  def ignored?
    @inquiry.nil? || @inquiry.empty?
  end
end
