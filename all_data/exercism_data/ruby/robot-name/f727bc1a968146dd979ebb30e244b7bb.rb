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
    characters = ["A", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R", "T", "V", "W", "X", "Y", "Z"] 
    numbers = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9] 
    chars_of_name = (0...2).map{ characters.to_a[rand(characters.size)] }.join
    nums_of_name = (0...3).map{ numbers.to_a[rand(numbers.size)] }.join
    chars_of_name + nums_of_name
  end
end
