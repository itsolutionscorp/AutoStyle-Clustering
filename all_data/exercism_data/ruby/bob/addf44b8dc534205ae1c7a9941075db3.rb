class Bob
  def hey(user_input)
    if user_input.empty? || user_input.rstrip.empty?
      'Fine. Be that way!'
    elsif user_input.upcase == user_input
      'Woah, chill out!'
    elsif user_input.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
