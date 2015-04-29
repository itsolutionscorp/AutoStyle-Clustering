class Raindrops
  
  
  @@Prime_numbers = []
  @@Raindrop_code = { 3 => 'Pling', 5 => 'Plang', 7 => 'Plong' }
  
  def self.insert_prime(num)

    @@Prime_numbers.empty? ? ele = 2 : ele = @@Prime_numbers[-1] + 1
    
    loop do 
      (2..ele).each {|e|
        if ele % e == 0 and ele == e
          @@Prime_numbers << e
        end
      }
      break if num % ele == 0
      ele += 1      
    end # loop end
    
    
  end # insert_prime end 
  
  
  
   
  def self.convert(num)
    
    raindrop_numbers = []
    
    return "1" if num == 1
   
    loop do #-----------------------------------------------------------------------
        
      break if num == 1
      self.insert_prime(num) if @@Prime_numbers.empty?
    
      @@Prime_numbers.each_with_index do |p,idx|
        if num % p == 0
          num /= p
          raindrop_numbers << p
          break
        end
        self.insert_prime(num) if idx + 1 == @@Prime_numbers.size
      end #@@Prime_numbers end
      
    
    end #end loop do -----------------------------------------------------------------
    
    raindrop_copy = raindrop_numbers.uniq 
    str = ''
    raindrop_copy.each do |e|
      str += @@Raindrop_code[e].to_s 
    end
    str.length > 0 ? str  : raindrop_numbers.inject {|sum,e| sum *= e}.to_s
  end # convert end
      
end #class end
