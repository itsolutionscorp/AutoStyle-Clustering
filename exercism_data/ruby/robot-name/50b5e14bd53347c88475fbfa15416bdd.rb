class Robot
  def initialize
    generate_name
  end
  
  def name
    @name.to_s
  end

  def reset
    generate_name
  end

  private 
    def generate_name
      @name = ""
      letters = ('a'..'z').to_a.push( ('A'..'Z').to_a ).shuffle
      
      numbers = (1..9).to_a.shuffle

      2.times { @name += letters.pop }
      3.times { @name += numbers.pop.to_s }
    end

end
