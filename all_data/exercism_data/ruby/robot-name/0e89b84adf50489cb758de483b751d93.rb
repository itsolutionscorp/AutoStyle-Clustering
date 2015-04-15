class Robot
  def name
    l = (0...2).map{ ('A'..'Z').to_a[rand(26)]}.join
    n = (0...2).map{ (1...99999).to_a[rand(10000)]}.join[0..2]
    l + n
  end

  def reset
    name
  end
end
