class Robot
  attr_accessor :name

  @@names = []

  def initialize()
    @name = generate_name
    @@names << @name
  end

  def reset
    @@names.delete(name)
    initialize()
  end

  private

  def generate_name
    generated_name = ''

    loop do
      2.times { generated_name << ('A'..'Z').to_a.sample }
      3.times { generated_name << ('0'..'9').to_a.sample }
      break if !@@names.include? generated_name
    end

    generated_name
  end


end
