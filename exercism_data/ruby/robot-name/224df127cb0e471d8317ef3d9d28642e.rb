class Robot

  attr_reader :name

  def initialize
    reset
  end

  def reset
    generate_new_name
    @kill_humans = false # just to be safe
  end

  private

  def generate_new_name
    @name = random_char * 2 + random_number * 3
  end

  def random_char
    pick_random_from "ABCDEFGHIJKLMNOPQRSTUVWXYZ".chars
  end

  def random_number
    pick_random_from (0..9).each
  end

  def pick_random_from(enumerator)
    enumerator.to_a.sample.to_s
  end

end
