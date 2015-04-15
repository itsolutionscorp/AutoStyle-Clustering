require 'set'
class Robot

  attr_reader :name
  LETTERS = ('A'..'Z')
  NUMBERS = ('1'..'9')
  TAKEN_NAMES = Set.new

  def initialize
    @name  = generate_new_name
  end
  
  def reset
    TAKEN_NAMES.delete(@name)
    @name = generate_new_name
  end

  private

    def generate_new_name
      name = random_name
      until TAKEN_NAMES.add?(name)
        name = random_name
      end
      name
    end

    def random_name
      random_letter(2) << random_number(3)
    end

    def random_letter(n)
      n.times.inject('') { |str| str + LETTERS.to_a.shuffle.first } 
    end

    def random_number(n)
      n.times.inject('') { |str| str + NUMBERS.to_a.shuffle.first } 
    end

end
