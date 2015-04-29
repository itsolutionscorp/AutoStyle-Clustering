class Robot
  attr_reader :name

  @@used_names = []

  def initialize
    @name = generate_new_name
  end

  def reset
    @name = generate_new_name
  end

  private

  def generate_new_name(iteration = 0)
    # avoid infinite loop in case of 'name exhaustion' (no more names available)
    raise 'Name exhaustion: no more names available' if iteration >= 10

    name = ''
    2.times { name << (65 + rand(26)).chr }
    3.times { name << rand(9).to_s }

    if @@used_names.include?(name)
      generate_new_name(iteration + 1)
    else
      @@used_names << name
      return name
    end
  end

end
