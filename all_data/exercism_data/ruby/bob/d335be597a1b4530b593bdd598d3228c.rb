class Bob
  def hey comment
    if comment.to_s.strip.empty?
      'Fine. Be that way!'
    elsif comment.upcase == comment
      'Woah, chill out!'
    elsif comment.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
