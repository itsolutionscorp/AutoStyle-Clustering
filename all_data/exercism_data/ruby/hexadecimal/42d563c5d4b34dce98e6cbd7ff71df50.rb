class Hexadecimal
  
  
  def initialize(hex_string)
    @hex_string = hex_string
  end  
  
  def symbol_to_number(n)
    return n.to_i if n.match(/[0-9]/) 
    symbols = {a: 10, b: 11, c: 12, d: 13, e: 14, f: 15}
    symbols[n.to_sym]    
  end
  
  def to_decimal
    @hex_string.split("").reverse.each.with_index.inject(0) { |sum,(value,index)| 
      decimal_number = symbol_to_number(value)
      return 0 unless decimal_number      
      sum + decimal_number*16**index
    }
  end  
  
  
end  
