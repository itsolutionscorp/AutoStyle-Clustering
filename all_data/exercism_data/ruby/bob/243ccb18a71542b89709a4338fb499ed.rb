class Bob
  def hey(subject)
    if yelled_at? subject
      'Woah, chill out!'
    elsif question? subject 
      'Sure.'
    elsif silence? subject
       'Fine. Be that way!'
    else
      'Whatever.'
    end
  end

  def yelled_at?(subject)
    subject==subject.upcase && subject!=subject.downcase
  end

  def question?(subject)
    subject.end_with?('?')
  end

  def silence?(subject)
    subject.strip.empty?
  end

end
