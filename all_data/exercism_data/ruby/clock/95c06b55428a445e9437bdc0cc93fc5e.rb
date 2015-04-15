class Clock
  attr_accessor :hs, :min
    
  def initialize(hs, min)
    @hs, @min = hs, min
  end
  
  def self.at hs, min = 0
    new hs, min
  end
  
  def + min
    hs_sum = min / 60
    min_sum = min - (hs_sum * 60)
    sum_min min_sum
    sum_hs hs_sum
    self
  end
  
  def - min
    hs_sum = min / 60
    min_sum = min - (hs_sum * 60)
    sub_min min_sum
    sub_hs hs_sum  
    self
  end
  
  def to_s
    sprintf "%02d:%02d", @hs, @min 
  end
  
  def eql? other
     other.instance_of?(self.class) && self.hs == other.hs && self.min == other.min
  end

  def == other
    self.eql? other
  end
  
  private
  def sub_hs hs
    sub = @hs - hs
    sub < 0 ? @hs = (24 - hs): @hs -= hs
  end
  
  def sub_min min
    sub = @min - min
    if sub > 0
      @min = sub
    else
      pred_hs
      @min = 60 - min
    end
  end
  
  def sum_hs hs
    sum = @hs + hs
    sum > 23 ? @hs = (23 - @hs) : @hs += hs
  end
  
  def sum_min min
    sum = @min + min
    if sum <= 60
      @min = sum
    else
      next_hs
      @min = min - 60
    end
  end
  
  def next_hs
    @hs.next > 23 ? @hs = 0 : @hs.next
  end
  
  def pred_hs
    @hs.pred < 0 ? @hs = 23 : @hs = @hs.pred
  end
end
