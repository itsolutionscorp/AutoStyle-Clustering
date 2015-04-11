class Robot
  attr_reader :name

  @@taken_names = [] 

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    characters = ("A".."Z").to_a 
    numbers = (0..9).to_a 
    loop do
      chars_of_name = (0...2).map{ characters.to_a[rand(characters.size)] }.join
      nums_of_name = (0...3).map{ numbers.to_a[rand(numbers.size)] }.join
      name = chars_of_name + nums_of_name
      unless @@taken_names.include?(name)
        @@taken_names << name
        return name
      end
    end
  end
end
