class Robot
  def initialize
    @name = nil
  end

  def name
    @name ||= rand(65..90).chr + rand(65..90).chr + rand(1000).to_s.rjust(3, '0')
  end

  def reset
    @name = nil
  end
end
