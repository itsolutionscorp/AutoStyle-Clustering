class Year
  def self.leap? year
    return false if exception? year
    dividable_by_four? year
  end
  
  private
  
  def self.exception? year
     year % 100 == 0 && year % 400 != 0
   end
   
   def self.dividable_by_four? year
     year % 4 == 0
   end
end
