class Bob
  attr_reader :blatherings

  def hey blatherings
    @blatherings = blatherings
    return my_response
  end

  private

  def my_response
    if shouting?
      'Woah, chill out!'
    elsif asking_a_question?
      'Sure.'
    elsif silent?
      'Fine. Be that way!'
    else
      'Whatever.' # Wait, isn't this what teenager's say? Or am I just trying to be empathetic? (or just pathetic)
    end
  end

  def shouting?
    blatherings.upcase == blatherings && blatherings.upcase != blatherings.downcase
  end

  def asking_a_question?
    blatherings.end_with?('?')
  end

  def silent?
    blatherings.strip.empty?
  end

end
