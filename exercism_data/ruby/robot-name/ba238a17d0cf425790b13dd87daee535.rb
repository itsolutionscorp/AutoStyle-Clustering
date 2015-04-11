class Robot

  @@previous_names = []

  attr_reader :name

  def initialize
    generate_name
  end

  def generate_name
    new_name = ""
    new_name << (0..1).map { ('a'..'z').to_a[rand(26)] }.join.upcase
    new_name << (0..2).map { rand(0..9) }.join
    generate_name if @@previous_names.include? new_name
    @name = new_name
    @@previous_names.push(new_name)
  end

  def reset
    generate_name
  end

end
