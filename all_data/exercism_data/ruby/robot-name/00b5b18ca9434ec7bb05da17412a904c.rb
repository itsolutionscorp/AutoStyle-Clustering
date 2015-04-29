class Robot

  @@uniq_names = {}

  def name 
    @name ||= generate_name
    save_uniq_name(@name)
  end

  def generate_name
    potential_name = (("A".."Z").to_a.sample(2) + 3.times.map { rand(0..9) }).join
      generate_name if @@uniq_names.has_key?(potential_name)
    potential_name
  end

  def reset
    @name = nil
  end

  def save_uniq_name(name)
    @@uniq_names[name] = name
  end
end
