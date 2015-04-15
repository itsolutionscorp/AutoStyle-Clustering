require 'set'

class Robot
  attr_accessor :name

  def initialize
    reset
  end

  def reset
    @name = new_name
    @@used_names.add name
  end

  private

    LETTERS = ('a'..'z').to_a + ('A'..'Z').to_a
    NUMBERS = ('0'..'9').to_a

    @@used_names = Set.new

    def new_name
      candidate = generate_name
      while @@used_names.include? candidate
        candidate = generate_name
      end
      candidate
    end

    def generate_name
      ((0...2).map {LETTERS.sample} + (0...3).map {NUMBERS.sample}).join
    end
end
