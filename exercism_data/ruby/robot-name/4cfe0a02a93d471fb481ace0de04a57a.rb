class Robot

  attr_reader :name

  @@all_names = []

  def initialize
    @name = random_name
  end

  def reset
    @name = random_name
  end

  private

  def random_name
    (0...2).map{ ('a'..'z').to_a[rand(26)] }.join +
      (0...3).map{ (0..9).to_a[rand(10)] }.join
  end

end
