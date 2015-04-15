class School
  def to_hash
    @to_hash.sort_by(&:first).to_h
  end

  private
  def to_hash=(args)
    @to_hash[args[1]] << args[0]
    @to_hash[args[1]].sort!
  end

  def initialize
    @to_hash=Hash.new{|k,v| k[v]=[]}
  end

  public
  def add(name,grade)
    self.to_hash=[name,grade]
  end

  def grade(num)
    @to_hash[num]
  end
end
