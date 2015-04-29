class Robot
  def name
    @name ||= randomly_generate_name
  end
  
  def reset
    @name = nil
  end
  
  private
  
  def randomly_generate_name
    (0...2).map { (65 + rand(26)).chr }.join + rand(100..1000).to_s
  end
end
