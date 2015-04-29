class Robot
  def initialize
    prefix
    suffix
  end 

  def prefix
   @pre = ("A".."Z").to_a.shuffle.pop(2).join
  end

  def suffix
   @suf = (100..999).to_a.shuffle.pop
  end

  def name 
    p joined = "#{@pre}#{@suf}"
  end
  
  def reset
    prefix
    suffix
  end
end
