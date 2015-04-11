class Fixnum
  
  Rules = [["I","V"],["X","L"],["C","D"],["M"]]
  
  def to_roman
    
    val = []
    
    self.to_s.length.times do |idx|      
      num = self.to_s[self.to_s.length-idx-1].to_i
      num <  4 ? val << Rules[idx][0] * num                     :
      num == 4 ? val << Rules[idx][0] + Rules[idx][1]           :
      num <  9 ? val << Rules[idx][1] + Rules[idx][0] * (num-5) :
      num == 9 ? val << Rules[idx][0] + Rules[idx+1][0]         : nil
    end
    
    val.reverse.join('')
    
  end
  
end
