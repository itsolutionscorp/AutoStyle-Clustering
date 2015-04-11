class Robot
  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    grab_two_rand_letters + grab_three_rand_numbers
  end

  def grab_two_rand_letters
    (0...3).map { ('A'..'Z').to_a[rand(26)] }.join
  end

  def grab_three_rand_numbers
    (0...3).map { (1..9).to_a[rand(9)] }.join
  end
end
