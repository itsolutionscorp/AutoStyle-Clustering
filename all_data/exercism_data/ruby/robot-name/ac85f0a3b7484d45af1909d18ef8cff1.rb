class Robot

  def name
    @name ||= get_name
  end

  def reset
    @name = nil
  end


  private

  def get_name
    random_letters(2) + random_numbers(3)
  end

  def random_letters(count)
    (0...count).map { (65 + rand(26)).chr }.join
  end

  def random_numbers(count)
    (0...count).map { rand(9)}.join
  end
end
