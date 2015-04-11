class Robot

  def name
    @name ||= (0...2).map { (65 + rand(26)).chr }.join + Random.new.rand(100..999).to_s 
  end

  def reset
    @name = nil
  end

end
