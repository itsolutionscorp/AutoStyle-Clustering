class Robot
  attr_reader :name

  def initialize
    @name = generate_name
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    o = [('a'..'z'), ('A'..'Z')].map { |i| i.to_a }.flatten
    letters = (0...2).map { o[rand(o.length)] }.join

    "#{letters}#{rand(999)}"
  end
end
