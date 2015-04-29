class Robot

  def name
    @name ||= generate_random_name
  end

  def reset
    @name = nil
  end

private

  def generate_random_name
    chars   = ("AA".."ZZ"  ).to_a.sample
    numbers = ("000".."999").to_a.sample

    "#{ chars }#{ numbers }"
  end

end
