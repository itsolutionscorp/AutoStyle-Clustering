class Robot
  def initialize
    reset
  end

  def name
    @name ||= generate_name
  end

  def reset
    @name = nil
  end

  private

  def generate_name
    name =  2.times.inject(:+) { ('a'..'z').to_a[rand(26)] }
    name += 3.times.inject(:+) { name += rand(9).to_s }
  end
end
