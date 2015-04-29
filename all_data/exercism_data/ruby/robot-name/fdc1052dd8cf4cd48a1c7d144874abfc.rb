class Robot
  attr_reader :name
  ALPHABET = ("A".."Z").to_a.freeze
  NUMBERS = (0..9).to_a.freeze

  @@taken_names = [] 

  def initialize
    reset
  end

  def reset
    @name = generate_name
  end

  private

  def generate_name
    loop do
      chars_of_name = (0...2).map{ ALPHABET.to_a[rand(ALPHABET.size)] }.join
      nums_of_name = (0...3).map{ NUMBERS.to_a[rand(NUMBERS.size)] }.join
      name = chars_of_name + nums_of_name
      unless @@taken_names.include?(name)
        @@taken_names << name
        return name
      end
    end
  end
end
