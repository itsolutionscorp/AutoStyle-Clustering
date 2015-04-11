class School
  
  def initialize
    @data = Hash.new([])
  end
  
  def add(name,score)
    @data[score] += [name] 
  end
  
  def grade(year)
    @data[year].sort
  end
  
  def to_hash 
    Hash[@data.sort].each {|x,y| @data[x].sort!}
  end
end
