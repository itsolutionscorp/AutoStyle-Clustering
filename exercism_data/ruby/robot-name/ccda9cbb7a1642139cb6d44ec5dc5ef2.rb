class Robot

  attr_reader :name

  def initialize
    @name ||= assign_random_name
  end

  def reset
    @name = String.new << assign_random_name
  end

  private

  def assign_random_name
    random_letters(2) << random_numbers(3)
  end

  def random_letters(num)
    num.times.inject('') { |text| text << ('A'..'Z').to_a.sample }
  end

  def random_numbers(num)
    prng = Random.new
    num.times.inject('') { |text| text << prng.rand(0..9).to_s }
  end

end
