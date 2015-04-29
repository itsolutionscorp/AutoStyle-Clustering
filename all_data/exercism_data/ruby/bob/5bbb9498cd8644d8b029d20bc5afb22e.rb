class Bob
  def hey(saying)
    if saying.strip.empty?
      'Fine. Be that way!'
    elsif saying.upcase == saying && saying.downcase != saying
      'Woah, chill out!'
    elsif saying.end_with?('?')
      'Sure.'
    else
    'Whatever.'
    end
  end
end
