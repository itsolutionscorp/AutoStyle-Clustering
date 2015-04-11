class Robot
  def initialize
    reset
  end

  def name
    @name = generate_name if @name.empty?
    @name
  end

  def reset
    @name = ''
  end

  private
  
  def generate_name
    loop do
      test = [*('A'..'Z')].sample(2).join + [*('000'..'999')].sample(1)[0]
      return test if @name != test
    end
  end
end
