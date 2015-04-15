class Robot

  def name
    @name ||= make_name
  end

  def make_name
    name = ''
    2.times { name << get_rand_letter }
    3.times { name << get_rand_number }
    name
  end

  def get_rand_letter
    letters = ('a'..'z').to_a + ('A'..'Z').to_a
    letters.shuffle.pop
  end

  def get_rand_number
    rand(10).to_s
  end

  def reset
    @name = nil
  end
end
