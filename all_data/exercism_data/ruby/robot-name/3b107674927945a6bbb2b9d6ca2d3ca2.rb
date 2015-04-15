class Robot

  def initialize
    @name = (0...2).map { (65 + rand(26)).chr }.join + rand(100..999).to_s
  end

  def name
    @name ||= (0...2).map { (65 + rand(26)).chr }.join + rand(100..999).to_s
  end

  def reset
    @names.delete(@name)
    @name = nil
  end
end
