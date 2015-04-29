# robot.rb
# Robot exercise

class Robot  
  def initialize
    reset
  end
  
  def reset
    @name=''
  end
  
  def rand(r)
    r[Random.rand(r.size)]
  end
  
  def name
    if @name.empty?
      a=('A'..'Z').to_a
      n=('0'..'9').to_a
      pattern=[a,a,n,n,n]
      pattern.each do |e|
	@name+=rand(e)
      end
    end
    @name
  end
end
