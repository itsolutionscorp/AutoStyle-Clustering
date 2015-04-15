class Robot

  Rand = Random.new
  InvalidNames = [nil]

  def initialize
    @name = nil
  end

  def self.name
    a_name = nil
    while InvalidNames.include? a_name do
      s = ("A".."Z").to_a.sample(2).join
      n = '%03d' % Rand.rand(0..999)
      a_name = s+n
    end
    a_name
  end

  def name
    # binding.pry
    @name ||= Robot.name
    @name
  end

  def reset
    @name = nil
  end

end
