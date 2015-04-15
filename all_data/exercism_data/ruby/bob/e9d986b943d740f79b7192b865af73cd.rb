# I think this one is the smallest so far.
class Bob
  def hey(text)
    input = text.to_s
    if input.empty?
      'Fine. Be that way.'
    elsif input == input.upcase
      'Woah, chill out!'
    elsif input.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
