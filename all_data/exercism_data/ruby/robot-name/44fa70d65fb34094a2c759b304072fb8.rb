class Robot
  LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".split("")
  @@current_names = []

  def initialize
    @name = generate_random_name
  end

  def name=(new_name)
    @name = new_name
  end

  def name
    @name
  end

  def generate_random_name
    random_name = LETTERS.sample + LETTERS.sample + ("%03d" % rand(999))
    if @@current_names.include?(random_name)
      generate_random_name
    else
      @@current_names << random_name
      return random_name
    end
  end

  def reset
    old_name = @name
    new_name = generate_random_name
    @@current_names.delete(old_name)
    @name = new_name
  end
end
