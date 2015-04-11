class Robot
  def name
    letters = ('A'..'Z').to_a.sample(2)
    numbers =     (0..9).to_a.sample(3)
    @name ||= (letters + numbers).join
  end

  def reset
    @name = nil
  end
end
