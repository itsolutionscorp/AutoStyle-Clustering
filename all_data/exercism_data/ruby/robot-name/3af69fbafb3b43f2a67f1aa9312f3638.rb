class Robot

  @@names_taken = []

  def initialize
    @name = name
    @@names_taken << @name
  end

  def name
    new_name = (0...2).map { (65 + rand(26)).chr }.join + rand(100..1000).to_s while @@names_taken.include?(new_name)
    @name ||= new_name
  end

  def reset
    @name = nil
  end
end
