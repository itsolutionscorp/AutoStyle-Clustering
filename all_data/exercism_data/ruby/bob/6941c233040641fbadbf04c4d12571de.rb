class Bob
  def hey msg
    @msg = msg
    return 'Fine. Be that way!' if empty?
    return "Sure." if question?
    return "Woah, chill out!" if yell? 
    "Whatever."
  end

  def question?
    question_with_only_numbers? || (end_with_?  && !identical_upcase?)
  end

  def yell?
     identical_upcase? && !full_number?
  end

  def full_number? 
    @msg.gsub(/[\d|,|\s]*/,'').empty?
  end

  def end_with_?
    @msg =~ /\?\Z/
  end

  def identical_upcase?
    @msg == @msg.upcase
  end

  def question_with_only_numbers?
    @msg.gsub(/\d*/,'') == '?'
  end
 
  def empty?
    @msg =~ /\A\s*\Z/
  end
end
