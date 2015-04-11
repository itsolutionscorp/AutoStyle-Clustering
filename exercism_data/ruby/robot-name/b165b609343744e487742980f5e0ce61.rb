# robot.rb
# Robot exercise

require 'set'

class Robot
  @@names=Set.new([])
  
  def initialize
    reset
  end
  
  def name
    gen_name if @name.empty?
    @name
  end
  
  def reset
    @name=''
  end
  
  def rand(r)
    r[Random.rand(r.size)]
  end
  
  def gen_name
    a=('A'..'Z').to_a
    n=('0'..'9').to_a
    begin
      reset  
      pattern=[a,a,n,n,n]
      pattern.each { |e| @name+=rand(e) }
    end while @@names.include?(@name)
    @@names.add @name
  end
end
