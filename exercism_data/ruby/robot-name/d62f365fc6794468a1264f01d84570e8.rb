class Robot
  def generate_name
    [('A'..'Z').to_a.sample, ('A'..'Z').to_a.sample, (1..9).to_a.sample, (1..9).to_a.sample, (1..9).to_a.sample]
  end

  def name
    @name ||= generate_name.join("")
  end

  def reset
    @name = generate_name.join("")
  end

end
