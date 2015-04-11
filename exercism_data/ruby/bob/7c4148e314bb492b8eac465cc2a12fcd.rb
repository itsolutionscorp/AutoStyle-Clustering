class Bob
  attr_reader :heard
  
  def hey(said)
    heard(said)
  end

  def responses
    {
      :chill    => "Woah, chill out!",
      :whatever => "Whatever.",
      :sure     => "Sure.",
      :fine     => "Fine. Be that way!"
    }
  end

  private

  def heard(said)
    thought = process_what_was(said)
    responses.fetch(thought) { "Whatever." }
  end

  def process_what_was(said)
    if mute?(said)
      :fine
    elsif being_shouted_at?(said)
      :chill
    elsif being_asked?(said)
      :sure
    else
      :whatever
    end
  end

  def being_asked?(said)
    said.end_with?("?")
  end

  def mute?(said)
    said.nil? || said == ""
  end

  def being_shouted_at?(said)
    said == said.upcase
  end

end
