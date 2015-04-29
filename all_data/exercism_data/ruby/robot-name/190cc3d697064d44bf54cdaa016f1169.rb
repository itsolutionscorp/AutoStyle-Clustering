class Robot
  attr_reader :name
  @@used_names = []

  def initialize
    @name = ''
    generate_name
  end

  def reset
    @name = ''
    generate_name
  end

  private

  def generate_name
    until !@@used_names.include?(@name)
      2.times { @name << ('A'..'Z').to_a.sample }
      3.times { @name << (0..9).to_a.sample.to_s }
    end
    @@used_names << @name  
  end
end
