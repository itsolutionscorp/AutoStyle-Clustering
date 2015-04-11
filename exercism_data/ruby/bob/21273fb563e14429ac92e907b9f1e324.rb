class Bob
  def hey(string)
    return 'Fine. Be that way.' if empty_arguments?(string)
    if string.upcase == string
      'Woah, chill out!'
    elsif string.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def empty_arguments?(string)
    string.nil? || string.empty?
  end
end
