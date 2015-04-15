class Robot
  def name
    @name ||= (("A".."Z").to_a.sample(2) + (0..9).to_a.sample(3)).join
  end

  def reset
    @name = nil
  end
end
