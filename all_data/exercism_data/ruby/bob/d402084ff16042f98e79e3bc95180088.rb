class Bob
  def hey(incoming)
    if nothing?(incoming)
      'Fine. Be that way!'
    elsif yelling?(incoming)
      'Woah, chill out!'
    elsif asking?(incoming)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing?(incoming)
    incoming.to_s.strip.empty?
  end

  def yelling?(incoming)
    incoming == incoming.upcase
  end

  def asking?(incoming)
    incoming.end_with?('?')
  end
end
