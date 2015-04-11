# class to compute pseudo-random robot names
class Robot
  def name
    @name ||= pseudo_random_name
  end

  def reset
    @name = nil
  end

  private

  def pseudo_random_name
    ('A'..'Z').to_a.sample(2).join.upcase + rand(100...999).to_s
  end
end
