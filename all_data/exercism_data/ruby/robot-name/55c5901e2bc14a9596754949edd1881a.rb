class Robot
  ALPHA = (?A..?Z).to_a
  DIGIT = (?0..?9).to_a
  def name
    @name ||= (2.times.map{ALPHA.sample} + 3.times.map{DIGIT.sample}).join
  end
  def reset
    @name = nil
  end
end
