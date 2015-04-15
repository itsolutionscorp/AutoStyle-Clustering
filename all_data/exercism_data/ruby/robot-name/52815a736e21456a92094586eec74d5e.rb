class Robot
  attr_reader :name
  @@names = []
  def initialize
    choose_name
  end

  def reset
    choose_name
  end

  private

  def choose_name
    @name = (('a'..'z').to_a + ('A'..'Z').to_a).sample(2).join + rand(100..999).to_s
    while @@names.include? @name
      @name = choose_name
    end
    @@names.push(@name)
  end
      
end
