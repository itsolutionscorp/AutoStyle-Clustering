class Robot

  def name
    @name ||= ('a'..'z').to_a.shuffle[0, 2].join + rand(100..999).to_s
  end

  def reset
    @name = nil
  end
end
