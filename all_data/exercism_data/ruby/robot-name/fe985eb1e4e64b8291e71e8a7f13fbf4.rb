class Robot
  def name
    @name ||= [*('A'..'Z')].sample(2).join + rand(100..999).to_s
  end

  def reset
    @name = nil
  end
end
