class Robot

  @@letters = ('a'..'z').to_a + ('A'..'Z').to_a

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private
  def generate_name
    name = generate_letters(2) + generate_numbers(3)
    return name
  end

  def generate_letters(num)
    (1..num).map{ @@letters.sample }.join
  end

  def generate_numbers(num)
    (1..num).map{ rand(10) }.join
  end

end
