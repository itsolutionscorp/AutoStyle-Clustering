class Robot

  def name
    @name ||= robot_name
  end

  def reset
    @name = nil
  end

  private

  def robot_name
    (0...2).map { ('A'..'Z').to_a[rand(26)] }
      .join + (0...3)
      .map { (0..9).to_a[rand(10)] }
      .join
  end

end
