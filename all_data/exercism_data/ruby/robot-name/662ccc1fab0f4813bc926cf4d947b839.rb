class Robot
  CHAR_RANGE = (65..90).to_a + (97..122).to_a

  def name
    @name ||= new_name
  end

  def reset
    @name = new_name
  end

  private

  def new_name
    2.times.map{ CHAR_RANGE.sample.chr }.join << 3.times.map{ rand(10) }.join
  end
end
