class Robot
  LETTERS = %w{A C D E F G H J K M N P Q R T V W X Y Z}
  NUMBERS = %w{0 1 2 3 4 5 6 7 8 9}
  @@names = []

  def create_unique_random_name
    loop do
      create_random_name
      break unless @@names.include?(@name)
    end
  end

  def create_random_name
    @name = ""
    2.times {@name << LETTERS.sample}
    3.times {@name << NUMBERS.sample}
  end

  def initialize
    create_unique_random_name
  end

  def reset
    create_unique_random_name
  end

  def name
    @name
  end

end
