class Robot
  attr_accessor :name

  @@names = []

  def initialize()
    @name = generate_name
    @@names << @name
  end

  def generate_name
    generated_name = ''
    
    loop do
      2.times{ generated_name << (65 + rand(25)).chr }
      3.times{ generated_name << rand(10).to_s }
      break if !@@names.include? generated_name
    end

    generated_name
  end

  def reset
    initialize()
  end
end
