class Robot

  def name
    @name ||= random_name
  end

  def reset
    @name = nil
  end

  def random_name
    "#{code}#{number}"
  end

  def code
    (0..1).map { ('A'..'Z').to_a[rand(26)] }.join
  end

  def number
    (0..2).map { (0..9).to_a[rand(10)] }.join
  end
end
