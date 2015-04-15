class Robot
  ALPHA = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars.to_a
  NUMERIC = (0..9).to_a

  def name
    @name ||= 2.times.map { ALPHA.sample }.concat(
        3.times.map { NUMERIC.sample }).join('')
  end
  
  def reset
    @name = nil
  end
end
