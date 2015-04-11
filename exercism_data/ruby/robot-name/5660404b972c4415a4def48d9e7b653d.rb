class Robot
  def initialize
    pat = {2 => ('A'...'Z').to_a, 3 => ('0'...'9').to_a}
    @name = pat.map{ |(k, v)| k.times.collect { v.sample }}.join
  end
  def reset
    initialize
  end
  def name
    @name
  end
end
