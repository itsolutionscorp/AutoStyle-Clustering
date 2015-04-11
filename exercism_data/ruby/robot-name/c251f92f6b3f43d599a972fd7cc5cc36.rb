class Robot
  def name
    @name ||= "#{('A'..'Z').to_a.sample(2).join('')}#{rand(100...999)}"
  end

  def reset
    @name = nil
  end
end
