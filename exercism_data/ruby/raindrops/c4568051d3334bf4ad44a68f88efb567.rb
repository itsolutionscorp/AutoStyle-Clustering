class Raindrops
  
  def self.insert_prime(num,prime_numbers)

    prime_numbers.empty? ? ele = 2 : ele = prime_numbers[-1] + 1    
    ele += 1 until num % ele == 0          
    prime_numbers << ele  
    
  end 
  
  
  def self.convert(num)
    
    return "1" if num == 1
    
    prime_numbers = []
    raindrop_numbers = []
    raindrop_code = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
    self.insert_prime(num,prime_numbers)
    
    
    until num == 1 #-----------------------------------------------------------------------
        
      prime_numbers.each_with_index do |p,idx|
        if num % p == 0
          num /= p
          raindrop_numbers << p
          break
        end
        self.insert_prime(num,prime_numbers) if idx + 1 == prime_numbers.size
      end 
      
    end #until end  do -----------------------------------------------------------------
    
    
    str = ''
    raindrop_numbers.uniq.each do |e|
      str += raindrop_code[e].to_s 
    end
    str.length > 0 ? str  : raindrop_numbers.inject {|sum,e| sum *= e}.to_s
    
    
  end # convert end
      
      
      
end #class end
