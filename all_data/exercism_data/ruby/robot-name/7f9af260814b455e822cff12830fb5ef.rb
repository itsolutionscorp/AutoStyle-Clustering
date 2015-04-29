class Robot
  attr_reader :name

  @@names = []

  def initialize
    set_name
  end

  def set_name
    @name = generate_name
    @@names << @name
  end

  def reset
    @@names.reject! { |n| n == @name }
    set_name
    @@names << @name
  end

  def name=(new_name)
    @@names << new_name
    @name = new_name
  end

  private
  def generate_name
    val = ''
    loop do

      2.times do 
        val += ('A'..'Z').to_a.sample
      end

      3.times do
        val += (0..9).to_a.sample.to_s
      end
      break unless @@names.include? val
    end

    val
  end
end
