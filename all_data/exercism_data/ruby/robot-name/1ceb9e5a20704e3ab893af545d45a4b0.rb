class Robot
  def initialize
    reset
  end
  def reset
    @@botname = ''
    2.times {@@botname << (65 + rand(26)).chr}
    3.times {@@botname << (rand(10)).to_s}
  end
  def name
    @@botname
  end
end
