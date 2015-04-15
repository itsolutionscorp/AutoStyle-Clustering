class Bob
  def hey(sentence)
    if blank?(sentence)
      'Fine. Be that way.'
    elsif all_caps?(sentence)
      'Woah, chill out!'
    elsif ends_with_question_mark?(sentence)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def blank?(sentence)
    sentence.nil? || sentence.empty?
  end

  def all_caps?(sentence)
    # contains a uppercase letter and no lower case letters
    sentence =~ /[A-Z]/ && sentence =~ /^[^a-z]*$/
  end

  def ends_with_question_mark?(sentence)
    sentence =~ /\?$/
  end
end
