class Robot
  def name
    @name ||= initials + sequence
  end

  def reset
    @name = nil
  end

  private
  def initials
    [*'A'..'Z'].sample(2).join
  end

  def sequence
    "#{ Random.rand(100..999) }"
  end
end
