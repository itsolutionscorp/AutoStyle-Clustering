# bob: First exercise in exercism.io Ruby track

class Bob
  def hey(inquire)
    inquire = inquire.strip
    if inquire == inquire.upcase && !inquire.empty?
      'Woah, chill out!'
      elsif inquire.end_with?('?')
        'Sure.'
      elsif !inquire.empty?
        'Whatever.'
      else
        'Fine. Be that way!'
    end
  end
end
