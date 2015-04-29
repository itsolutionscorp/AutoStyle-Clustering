class Robot
  attr_reader :name
  
  def initialize
    @name = generate
  end
  
  def reset
    until @name != (@reset_name ||= generate) do
      @reset_name = generate
    end
    @name = @reset_name
  end
  
  private
  
    def generate
      (0...2).map { (65 + rand(26)).chr }.join << rand(100..999).to_s
    end
  
end
