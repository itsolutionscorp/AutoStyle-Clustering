class Robot
  def name
    @name ||= begin
      two_random_chars = (1..2).map { (rand 65..90).chr }.join
      three_random_ints  = (1..3).map { rand(9) }.join

      two_random_chars + three_random_ints
    end
  end

  def reset
    @name = nil
  end
end
