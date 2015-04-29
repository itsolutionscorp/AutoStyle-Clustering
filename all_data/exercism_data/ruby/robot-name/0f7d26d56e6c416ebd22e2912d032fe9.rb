require 'securerandom'
class Robot
  attr_accessor :name
  @@used_names = []

  def initialize
    @name = ''
    create_name
  end

  def reset
   @name = ''
   create_name
  end

  private

  def create_name
    until !@@used_names.include?(@name)
      2.times { @name << ('A'..'Z').to_a.sample }
      3.times { @name << (0..9).to_a.sample.to_s }    end
    @@used_names << @name
  end
end
