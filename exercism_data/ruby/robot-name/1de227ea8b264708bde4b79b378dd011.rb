class Robot

  attr_reader :name

  @@all_names = []

  def initialize
    @name = random_name
    @@all_names << @name
  end

  def reset
    @@all_names.delete(@name)
    @name = random_name
  end

  private

  def random_name
    candidate = ""
    loop do
      candidate = (0...2).map{ ('a'..'z').to_a[rand(26)] }.join +
        (0...3).map{ (0..9).to_a[rand(10)] }.join
      break unless @@all_names.include? candidate
    end
    candidate
  end

end
