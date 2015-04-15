class BinarySearch
  
  attr_accessor :list
  
  def initialize(orig)
    raise ArgumentError unless sorted? orig
    self.list = orig   
  end
  
  def search_for(n)   
    s_list = self.list 
    offset = 0
    loop do
      mid = s_list.length/2
      left = s_list[0..mid-1]
      right = s_list[mid+1..s_list.length-1]
      mid_val = s_list[mid]
      if n == mid_val
        return offset+mid 
      elsif n > mid_val
        offset += mid+1
        s_list = right
      else
        s_list = left
      end    
      raise RuntimeError if s_list.empty?
    end
  end
  
  def middle
   self.list.length/2
  end  
  
  private
  
  def sorted?(orig)
    for i in 1..orig.length-1
      return false if orig[i] < orig[i-1]
    end 
    return true   
  end  
    
end
