class Bob
  def hey(greeting)
    if nothing?(greeting)
      'Fine. Be that way!'
    elsif yelling?(greeting)
      'Woah, chill out!'
    elsif question?(greeting)
      'Sure.'
    else
      'Whatever.'
    end
  end

  def nothing?(greeting)
    greeting.nil? or greeting.empty?
  end

  def question?(greeting)
    greeting.end_with?('?')
  end

  def yelling?(greeting)
    greeting == greeting.upcase
  end
end
