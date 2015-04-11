class Robot

  attr_reader :name
  LETTERS = ('A'..'Z')
  NUMBERS = ('1'..'9')

  def initialize
    set_name
  end
  
  def reset
    set_name
  end

  private
    def get_random_name
      (LETTERS.to_a.shuffle[0..1] << NUMBERS.to_a.shuffle[0..2]).join
    end

    def set_name
      @name = get_random_name
    end

end
