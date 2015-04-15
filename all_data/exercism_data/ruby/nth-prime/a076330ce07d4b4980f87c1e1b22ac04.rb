require 'prime'
class Prime

  def self.nth(num)
       Prime.first(num+1)[num-1]
    
  end
  
end
