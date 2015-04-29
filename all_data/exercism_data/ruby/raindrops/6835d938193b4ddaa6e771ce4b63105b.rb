class Raindrops
  RAINDROPS_TO_STRING = {
    3 => 'Pling',
    5 => 'Plang',
    7 => 'Plong'
  }

  def self.convert(raindrops)
    new(raindrops).to_s
  end

  attr_reader :raindrops

  def initialize(raindrops)
    @raindrops = raindrops
  end

  def to_s
    result = ''
    RAINDROPS_TO_STRING.keys.each do |k|
      result << RAINDROPS_TO_STRING[k] if raindrops % k == 0
    end
    result << raindrops.to_s if result.length == 0
    result
  end
end
