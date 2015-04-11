class Bob

  # @param drivel [String] The drivel Bob is responding to
  # @return response [String] Bob's response to the drivel
  def hey(drivel)
    @drivel = drivel
    case
    when silent_drivel? then annoyed_response
    when yelled_drivel? then alarmed_response
    when asked_drivel?  then resigned_response
    else                indifferent_response
    end
  end

  private

  def drivel
    @drivel
  end

  def silent_drivel?
    drivel.to_s.strip == ''
  end

  def annoyed_response
    "Fine. Be that way!"
  end

  def yelled_drivel?
    drivel.upcase == drivel
  end

  def alarmed_response
    "Woah, chill out!"
  end

  def asked_drivel?
    drivel.end_with?('?')
  end

  def resigned_response
    "Sure."
  end

  def indifferent_response
    "Whatever."
  end

end
