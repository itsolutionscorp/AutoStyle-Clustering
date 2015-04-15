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
      letters = ('a'..'z').to_a.push( ('A'..'Z').to_a )
      
      numbers = (1..9).to_a

      2.times { @name += letters.sample }
      3.times { @name += numbers.sample.to_s }
    end

end
