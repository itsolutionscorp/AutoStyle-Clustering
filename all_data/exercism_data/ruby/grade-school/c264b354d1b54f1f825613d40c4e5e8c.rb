class School
  def initialize()
    @list = Hash.new { |list, grade| list[grade] = [] }
  end

  def add(name,grade)
    @list[grade] << name
  end
  
  def to_hash
    sorted = @list.map{|grade,names| [grade,names.sort]}.sort
    Hash[sorted]
  end

  def grade(level)
    @list[level].sort
  end


end
