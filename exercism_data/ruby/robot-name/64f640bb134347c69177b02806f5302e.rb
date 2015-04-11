require 'set'
class Robot
  attr_reader :name
  @@used_names = Set.new
  LETTERS = ("A".."Z").to_a
  NUMBERS = (0..9).to_a

  def initialize
    reset
  end

  def reset
    tmp_name = generate_name 
    while @@used_names.include? tmp_name 
      tmp_name = generate_name 
    end
    @@used_names << tmp_name
    @name = tmp_name
  end

  def generate_name
    @name = "#{alpha_prefix}#{numeric_suffix}"
  end

  def alpha_prefix(size=2)
    LETTERS.sample(size).join
  end
  
  def numeric_suffix(size=3)
    NUMBERS.sample(size).join
  end
end
