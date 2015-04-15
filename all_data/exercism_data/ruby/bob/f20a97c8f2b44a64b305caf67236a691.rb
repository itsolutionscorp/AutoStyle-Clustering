class Bob
  attr_reader :heard

  def hey(heard)
    @heard = heard
    respond!
  end

  def response_pool
    {
      :chill    => "Woah, chill out!",
      :whatever => "Whatever.",
      :sure     => "Sure.",
      :fine     => "Fine. Be that way!"
    }
  end

  def respond!
    thought = process_what_was_said
    response_pool.fetch(thought) { "Whatever." }
  end

  def process_what_was_said
    if being_shouted_at?
      :chill
    elsif asked_politely?
      :sure
    elsif mute?
      :fine
    # Actually redundant at this stage as everything else is a "whatever", left in for clarity
    elsif were_forceful? || used_acronyms?
      :whatever
    else
      :whatever
    end
  end

  ALL_CAPITALS            = %r{^[A-Z0-9,]+\s+[A-Z0-9,]+}
  SENTENCE                = %r{^[A-Z]?[a-z\s]+}
  ACRONYMS                = %r{[A-Z]{2,}}

  private

  def being_shouted_at?
    shouting_statement? or shouting_question? or shouting?
  end

  def asked_politely?
    heard =~ SENTENCE and heard.end_with?("?")
  end

  def were_forceful?
    heard =~ ALL_CAPITALS
  end
  
  def used_acronyms?
    heard =~ ACRONYMS
  end

  def mute?
    heard.nil? or heard == ""
  end

  def shouting_statement?
    shouting? and heard.end_with?("!")
  end

  def shouting_question?
    shouting? and heard.end_with?("?")
  end

  def shouting?
    heard =~ ALL_CAPITALS
  end

end
