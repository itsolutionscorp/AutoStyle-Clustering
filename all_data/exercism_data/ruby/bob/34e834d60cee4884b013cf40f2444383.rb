# bob: First exercise in exercism.io Ruby track

class Bob
  def hey(inquire)
    inquire.strip!
    if inquire.empty?
      'Fine. Be that way!'
    elsif inquire == inquire.upcase && !inquire.empty?
      'Woah, chill out!'
    elsif inquire.end_with?('?')
      'Sure.'
    else
      'Whatever.'
    end
  end
end
