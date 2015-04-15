class Bob
  def hey(something)
    case
    when nothing?(something)
      'Fine. Be that way!'
    when yelling?(something)
      'Woah, chill out!'
    when asking?(something)
      'Sure.'
    else
      'Whatever.'
    end
  end

  private

  def nothing?(something)
    # Blatantly stolen from ActiveSupport
    something.respond_to?(:empty?) ? something.empty? : !something
  end

  def yelling?(something)
    something.upcase == something
  end

  def asking?(something)
    something.end_with?('?')
  end
end
