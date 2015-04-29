class Bob
  attr_reader :blatherings

  def hey blatherings
    @blatherings = blatherings
    try_to_understand_the_teenager blatherings
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

  def try_to_understand_the_teenager(blatherings)
    @silent = true if blatherings.strip.empty?
    @shouting = true if blatherings.upcase == blatherings && blatherings.upcase != blatherings.downcase
    @asking_a_question = true if blatherings.end_with?('?')
  end

  def reset_understandings
    @silent = nil; @shouting = nil; @asking_a_question = nil
  end

  def shouting?; !!@shouting end
  def asking_a_question?; !!@asking_a_question end
  def silent?; !!@silent end

end
