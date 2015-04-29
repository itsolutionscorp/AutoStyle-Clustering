# robot.rb
# Robot exercise

class Robot  
  def initialize
    reset
  end
  
  def reset
    @name=''
  end
  
  def rand(range)
    r = range.to_a
    r[Random.rand(r.size)]
  end
  
  def name
    return unless @name.empty?
    a='A'..'Z'
    n='0'..'9'
    pattern=[a,a,n,n,n]
    pattern.each do |e|
      @name+=rand(e)
    end
    @name
  end
end
