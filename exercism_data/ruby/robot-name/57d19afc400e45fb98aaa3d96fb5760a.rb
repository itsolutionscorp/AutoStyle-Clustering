class Robot
  attr_reader :previous_names

  def initialize
    @previous_names = []
  end

  def name
    @name ||= make_name
  end

  def make_name
    name = ''
    2.times { name << get_rand_letter }
    3.times { name << get_rand_number }
    if @previous_names.include?(name)
      make_name
    else
      @previous_names << name
      name
    end
  end

  def get_rand_letter
    letters = ('a'..'z').to_a + ('A'..'Z').to_a
    letters.sample
  end

  def get_rand_number
    rand(10).to_s
  end

  def reset
    @name = nil
  end
end
