class Robot
  attr_reader :name
  @@used_names = []

  def initialize
    generate_name
  end

  def reset
    generate_name
  end

  private

  def generate_name
    loop do
      @name = ('A'..'Z').to_a.shuffle[0..1].join + rand(100..999).to_s
      break if !@@used_names.include? @name
    end
    @@used_names << @name unless @@used_names.include? @name
  end
end
