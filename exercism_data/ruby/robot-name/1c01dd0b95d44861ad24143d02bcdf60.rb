class Robot

  def name
    @name ||= "#{random_Letters}#{random_numbers}"
  end

  def reset
    @name = nil
  end

  private

  def random_Letters
    (0..1).map { ('A'..'Z').to_a[rand(26)] }.join
  end

  def random_numbers
    (0..2).map { (0..9).to_a[rand(10)] }.join
  end
end
