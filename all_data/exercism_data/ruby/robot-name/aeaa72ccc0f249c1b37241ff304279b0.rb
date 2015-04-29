class Robot

  def name
    @name ||= ([*('A'..'Z')].sample(2) + [*('1'..'9')].sample(3)).join
  end

  def reset
    @name = nil
  end

end
