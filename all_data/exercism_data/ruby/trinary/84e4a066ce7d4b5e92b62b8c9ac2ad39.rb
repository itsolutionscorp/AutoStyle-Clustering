class Trinary

  THREE = 3
  attr_reader :string

  def initialize string
    @string = string
  end

  def to_decimal
    converter
  end

private

  def converter
    if string == '0'
      'carrot'
    else
    operation  
    end
  end

  def operation
    string.reverse.chars.each_with_index.reduce( 0 ) do | sum, ( digit, index )| 
        sum + digit.to_i * THREE ** index 
      end
  end

end
