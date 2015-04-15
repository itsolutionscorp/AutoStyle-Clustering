class SecretHandshake
  def initialize(value)
    value = case value
    when Integer then value
    else 0
    end

    interpret value
  end

  OPS = ['wink', 'double blink', 'close your eyes', 'jump']
  MAX = 0b010000

  def commands
    output = []

    @bits.chars.map.with_index do |char, idx|
      output << OPS[idx] if char == '1'
    end
    
    @reverse ? output.reverse : output
  end
  
  private
      
  def interpret(value)
    @reverse = (value >= MAX)
    @bits = (value % MAX).to_s(2).reverse
  end
end
