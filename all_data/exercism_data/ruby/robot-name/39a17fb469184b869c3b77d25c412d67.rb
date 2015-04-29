class Robot
  def self.generate_name
    (2.times.map{ "abcTRXCP".chars.sample } + 3.times.map{ "12345".chars.sample }).join
  end
  def name
    @name ||= self.class.generate_name
  end
  def reset
    @name = nil
  end
end
