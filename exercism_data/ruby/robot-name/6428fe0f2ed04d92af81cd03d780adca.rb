class Robot
  def name
    @name ||= _name
  end

  def reset
    @name = nil
  end

  private
  def _name
    letters = ('A'..'Z').to_a
    res = letters[rand(26)]
    res << letters[rand(26)]
    res << "%03d" % rand(1000)
  end
end
