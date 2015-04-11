class Robot

  def name
    @name ||= generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    name = []
    2.times { name << generate_random_letter }
    3.times { name << generate_random_number }
    name.join
  end

  def generate_random_letter
    letters = ("a".."z").to_a
    letters[rand(26)]
  end

  def generate_random_number
    rand(9).to_s
  end

end
